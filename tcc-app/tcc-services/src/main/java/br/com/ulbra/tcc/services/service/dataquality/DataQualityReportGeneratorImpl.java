package br.com.ulbra.tcc.services.service.dataquality;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ulbra.tcc.common.dao.common.DaoUtil;
import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityReportVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorColumnRowVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorColumnVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;
import br.com.ulbra.tcc.services.common.CommonPropertyConfig;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskServiceImpl;
import br.com.ulbra.tcc.services.util.ServiceUtil;
import br.com.ulbra.tcc.services.util.ZipUtil;

/**
 * The DataQualityReportGeneratorImpl Class
 * 
 * @author Paulo Pacheco
 *
 */
@Service(ServiceBuilder.DATA_QUALITY_REPOR_GENERATOR)
public class DataQualityReportGeneratorImpl implements DataQualityReportGenerator {

	private static final Logger LOGGER = Logger.getLogger(DatabaseTaskServiceImpl.class);
	
	private static final String REPORT_TEMP_PATH = "dataquality.path.report.temp";
	
	private static final String GENERATED_REPORTS_PATH = "dataquality.path.report.success";
	
	private static final String OUTPUT_FILE_DATE_FORMAT = "yyyyMMddHHmmss";
	
	private static final String TXT_EXT = ".txt";
	
	private static final String ZIP_EXT = ".zip";
	
	private static final String REPORT_GENERATION_SUFFIX = "DQRG";
	
	private static final SimpleDateFormat SimpleDateFormat = new SimpleDateFormat(OUTPUT_FILE_DATE_FORMAT);
	
	@Autowired
	private transient CommonPropertyConfig config;

	public DataQualityReportVO startReportGeneration(final List<DataQualityValidatorVO> dataQualityValidatorVOs) 
			throws TCCTechnicalException, TCCBusinessException {	
		
		final String tempPath = config.getProperty(REPORT_TEMP_PATH);
		final String finalPath = config.getProperty(GENERATED_REPORTS_PATH);
		
		DataQualityReportVO dataQualityReportVO = new DataQualityReportVO();
		
		if(tempPath == null || finalPath == null){
			LOGGER.error("Cannot generate reports. Invalid path configuration.");
			throw new TCCTechnicalException("A Technical error ocorred. The directory configuration seems to be invalid.");
		}
		if(!isRecordAvailableToBeProcessed(dataQualityValidatorVOs)){
			dataQualityReportVO.setReportAvailable(false);	
		} else {
			
			final String lineSeparator = System.getProperty("line.separator");
			final String tempDirForRequest = getTempDirForRequest();
			
			for (DataQualityValidatorVO dataQualityValidatorVO : dataQualityValidatorVOs) {
				try {
					writeFile(tempPath, dataQualityValidatorVO, lineSeparator, tempDirForRequest);
					
				} catch (IOException ioe) {
					LOGGER.error("Error when creating file.", ioe);
					ServiceUtil.recursiveDelete(new File(tempPath));
					throw new TCCTechnicalException("A Technical error ocorred. Could not generate reports.", ioe);
				}
			}
			
			final String compressSrcFolder = tempPath + File.separator + tempDirForRequest;
			final String compressDestFolder = compressSrcFolder + ZIP_EXT;
			
			try {
				compressWorkDir(compressSrcFolder, compressDestFolder);
				
			} catch (IOException ioe) {
				LOGGER.error("Error when compressing/deleting folder.", ioe);
				ServiceUtil.recursiveDelete(new File(compressSrcFolder));
				throw new TCCTechnicalException("A Technical error ocorred. Could not generate reports.", ioe);
			}
			
			dataQualityReportVO.setReportZipFolderPath(compressDestFolder);
			dataQualityReportVO.setReportName(tempDirForRequest + ZIP_EXT);
			dataQualityReportVO.setReportAvailable(true);
		}
		return dataQualityReportVO;
	}
	
	private void writeFile(final String tempPathFile, final DataQualityValidatorVO dataQualityValidatorVO, 
			final String lineSeparator, final String uniqueDir) throws IOException {
		
		for (DataQualityValidatorColumnVO dataQualityValidatorColumnVO : dataQualityValidatorVO.getDataQualityValidatorColumnVOs()) {
			
			final String columnKey = dataQualityValidatorColumnVO.getColumn();
			
			final String columnDirPath = tempPathFile + File.separator + uniqueDir + File.separator + 
					DaoUtil.getSchemaFromColumnKey(columnKey) + File.separator + DaoUtil.getTableFromColumnKey(columnKey);
			
			File outputFile = new File(columnDirPath);
			
			if(!outputFile.exists()){
				boolean result = outputFile.mkdirs();
				
				if(!result){
					LOGGER.error("Could not create temp dir to store reports");
					return;
				}
			}
			
			final String fullFilePath = columnDirPath + File.separator + DaoUtil.getColumnFromColumnKey(columnKey) + TXT_EXT;
			outputFile = new File(fullFilePath);
			
			FileOutputStream fos = new FileOutputStream(outputFile);
			OutputStreamWriter outputStream = new OutputStreamWriter(fos);
			
			for (DataQualityValidatorColumnRowVO dataQualityValidatorColumnRowVO : dataQualityValidatorColumnVO.getDataQualityValidatorColumnRowVOs()) {
				outputStream.write(dataQualityValidatorColumnRowVO.getRow() + "      FAILED CHARS ------> " + 
						dataQualityValidatorColumnRowVO.getFailedChars().toString() + lineSeparator);
			}
			
			if(outputStream != null){
				outputStream.close();
			}
		}
	}
	
	private void compressWorkDir(final String src, final String dest) throws IOException{
		
		File file = new File(src);
		if(file.isDirectory()){
			LOGGER.info("Compressing dir " + file.getAbsolutePath());
			ZipUtil.compressArchive(src, dest);
			ServiceUtil.recursiveDelete(file);
		} else {
			LOGGER.error("Could not find dir to compress " + file.getAbsolutePath());
		}
	}
	
	private boolean isRecordAvailableToBeProcessed(final List<DataQualityValidatorVO> dataQualityValidatorVOs){
		for (DataQualityValidatorVO dataQualityValidatorVO : dataQualityValidatorVOs) {
			for (DataQualityValidatorColumnVO dataQualityValidatorColumnVOs : dataQualityValidatorVO.getDataQualityValidatorColumnVOs()) {
				if(dataQualityValidatorColumnVOs.getDataQualityValidatorColumnRowVOs() != null && 
						!dataQualityValidatorColumnVOs.getDataQualityValidatorColumnRowVOs().isEmpty()) {
					return true;	
				}
			}
		}		
		return false;
	}
	
	private String getTempDirForRequest(){
		return REPORT_GENERATION_SUFFIX + "_" + SimpleDateFormat.format(new Date());
	}	
}
