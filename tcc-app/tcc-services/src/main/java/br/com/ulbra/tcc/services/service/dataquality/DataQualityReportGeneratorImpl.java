package br.com.ulbra.tcc.services.service.dataquality;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.ulbra.tcc.services.util.ExportToExcel;
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
	private static final String FAILED_STATUS = "FAILED";
	private static final String OUTPUT_FILE_HEADER = "STATUS" + String.format("%70s", " ").replace(" ", " ") + "ROW";
	private static final String OUTPUT_FILE_HEADER_BREAKLINE = String.format("%80s", " ").replace(" ", "-");
	private static final SimpleDateFormat SimpleDateFormat = new SimpleDateFormat(OUTPUT_FILE_DATE_FORMAT);
	
	@Autowired
	private transient CommonPropertyConfig config;

	public DataQualityReportVO startReportGeneration(final List<DataQualityValidatorVO> dataQualityValidatorVOs) 
			throws TCCTechnicalException, TCCBusinessException {	
		
		final String tempPath = config.getProperty(REPORT_TEMP_PATH);	
		final String successPath = config.getProperty(GENERATED_REPORTS_PATH);
		
		DataQualityReportVO dataQualityReportVO = new DataQualityReportVO();
		
		if(tempPath == null || successPath == null){
			LOGGER.error("Cannot generate reports. Invalid path configuration.");
			throw new TCCTechnicalException("A Technical error occurred. The directory configuration seems to be invalid.");
		}
			
		final String lineSeparator = System.getProperty("line.separator");
		final String reportId = SimpleDateFormat.format(new Date());
		
		for (DataQualityValidatorVO dataQualityValidatorVO : dataQualityValidatorVOs) {
			try {
				writeFile(tempPath, dataQualityValidatorVO, lineSeparator, reportId);
				
			} catch (IOException ioe) {
				LOGGER.error("Error when creating file.", ioe);
				ServiceUtil.recursiveDelete(new File(tempPath));
				throw new TCCTechnicalException("A Technical error occurred. Could not generate reports.", ioe);
			}
		}
		
				
		final String tempReportFolder = tempPath + File.separator + REPORT_GENERATION_SUFFIX + "_" + reportId;
		final String compressDestFolder = tempReportFolder + ZIP_EXT;
		
		createExcelResume(dataQualityValidatorVOs, tempReportFolder);
		
		try {
			compressWorkDir(tempReportFolder, compressDestFolder);
			moveZipToSuccessFolder(compressDestFolder, successPath);
			
		} catch (IOException ioe) {
			LOGGER.error("Error when compressing/deleting folder.", ioe);
			ServiceUtil.recursiveDelete(new File(tempReportFolder));
			throw new TCCTechnicalException("A Technical error occurred. Could not generate reports.", ioe);
		}
		
		dataQualityReportVO.setReportId(reportId);
		dataQualityReportVO.setReportName(reportId + ZIP_EXT);
		dataQualityReportVO.setReportAvailable(true);
		return dataQualityReportVO;
	}
	
	public String getDownloadReportPath(String reportId) throws TCCTechnicalException, TCCBusinessException {
		
		final String successPath = config.getProperty(GENERATED_REPORTS_PATH);
		
		if(successPath == null){
			LOGGER.error("Cannot generate reports. Invalid path configuration.");
			throw new TCCTechnicalException("A Technical error occurred when trying to download the report. " + 
					"The directory configuration is invalid. Please contact system admin.");
		}
		
		String filePath = successPath + File.separator + REPORT_GENERATION_SUFFIX + "_" + reportId + ZIP_EXT;
		File file = new File(filePath);
		
		if(!file.exists()){
			throw new TCCTechnicalException("A Technical error occurred. Could not find report for download.");
		}
		return filePath;
	}
	
	private void writeFile(final String tempPathFile, final DataQualityValidatorVO dataQualityValidatorVO, 
			final String lineSeparator, final String uniqueDir) throws TCCTechnicalException, IOException {
		
		for (DataQualityValidatorColumnVO dataQualityValidatorColumnVO : dataQualityValidatorVO.getDataQualityValidatorColumnVOs()) {
			
			final String columnKey = dataQualityValidatorColumnVO.getColumn();
			
			final String columnDirPath = tempPathFile + File.separator + REPORT_GENERATION_SUFFIX + "_" + uniqueDir + File.separator + 
					DaoUtil.getSchemaFromColumnKey(columnKey) + File.separator + DaoUtil.getTableFromColumnKey(columnKey);
			
			File outputFile = new File(columnDirPath);
			
			if(!outputFile.exists()){
				boolean result = outputFile.mkdirs();
				
				if(!result){
					LOGGER.error("Could not create temp dir to store reports");
					throw new TCCTechnicalException("An error occorred. Could not create temporary dir to store reports.");
				}
			}
			
			final String fullFilePath = columnDirPath + File.separator + DaoUtil.getColumnFromColumnKey(columnKey) + TXT_EXT;
			outputFile = new File(fullFilePath);
			FileOutputStream fos = new FileOutputStream(outputFile);
			OutputStreamWriter outputStream = new OutputStreamWriter(fos);
			
			outputStream.write(OUTPUT_FILE_HEADER + lineSeparator);
			outputStream.write(OUTPUT_FILE_HEADER_BREAKLINE + lineSeparator);
						
			for (DataQualityValidatorColumnRowVO dataQualityValidatorColumnRowVO : dataQualityValidatorColumnVO.getDataQualityValidatorColumnRowVOs()) {
				outputStream.write(FAILED_STATUS + String.format("%70s", " ").replace(" ", " ") + dataQualityValidatorColumnRowVO.getRow() + lineSeparator);
				outputStream.write(dataQualityValidatorColumnRowVO.getFailedChars().toString() + lineSeparator + lineSeparator);
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
	
	private boolean moveZipToSuccessFolder(String from, String to) throws TCCTechnicalException{
				
		File fromFile = new File(from);
		File targetFolder = new File(to);
		
		if(!targetFolder.isDirectory()){			
			LOGGER.info("Creating target folder to store reports");
			
			if(targetFolder.mkdirs()){
				LOGGER.info("The target folder has been created at " + to);
			} else {
				LOGGER.error("Could not create target folder at " + to);
				throw new TCCTechnicalException("Could not create folder to store reports. Please check with Admin.");
			}
		}
		
		if(fromFile.exists()){
			final File moveFileTo = new File(to + File.separator + fromFile.getName());
			
			if(fromFile.renameTo(moveFileTo)){
				LOGGER.info("The file has been moved successfully");
			} else {
				LOGGER.error("The file was not moved correctly to success folder");
				throw new TCCTechnicalException("An error occorred when trying to move report file.");
			}
		} else {
			LOGGER.error("The file " + from + " was not found.");
			throw new TCCTechnicalException("An error occorred. Could not find report temporary folder.");
		}
		
		return false;
	}
	
	private void createExcelResume(List<DataQualityValidatorVO> dataQualityValidatorVOs, final String tempPath) throws TCCTechnicalException, TCCBusinessException{
		
        Map<Integer, String> headersMap = new HashMap<Integer, String>();
        headersMap.put(0, "Schema");
        headersMap.put(1, "Table");
        headersMap.put(2, "Column");
        headersMap.put(3, "#Number of Records");
        headersMap.put(4, "#Number of Failed Records");
        headersMap.put(5, "%Failed Records");
        headersMap.put(6, "Regular Expression");
        
        ExportToExcel.export(dataQualityValidatorVOs, headersMap, new File(tempPath));
	}
}
