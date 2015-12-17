package br.com.ulbra.tcc.services.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.ulbra.tcc.common.dao.common.DaoUtil;
import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorColumnVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;

/**
 * The ExportToExcel Class
 * 
 * @author Paulo Pacheco
 *
 */
@SuppressWarnings("deprecation")
public class ExportToExcel {

	private static final Logger LOGGER = Logger.getLogger(ExportToExcel.class);
	
	private static final String FONT_NAME = "IMPACT";
	private static final String SHEET_NAME = "data_quality";
	private static final String HEADER_NAME = "Data Quality Resume";
	private static final String EXCEL_RESUME_EXPORT_NAME = "dataQualityExcelResume.xlsx";
	
	public static void export(List<DataQualityValidatorVO> dataQualityValidatorVOs, Map<Integer, String> columnsMap, File path) 
			throws TCCTechnicalException, TCCBusinessException {
		
		try{
			
			if(columnsMap.isEmpty()){
				throw new TCCBusinessException("Invalid configuration to export results to Excel");
			}
			
			if(!path.exists()){
				boolean result = path.mkdir();
				if(!result){
					throw new TCCTechnicalException("Could not create directory to export results to Excel.");
				}
			}
			
			final String output = path.getAbsolutePath() + File.separator + EXCEL_RESUME_EXPORT_NAME;
			
			XSSFWorkbook workbook = new XSSFWorkbook(); 
	        XSSFSheet spreadsheet = workbook.createSheet(SHEET_NAME);
	        
	        int rowIndex = 0;
	        setExcelHeader(workbook, spreadsheet, columnsMap.size(), rowIndex, HEADER_NAME);
	        setColumnHeader(workbook, spreadsheet, columnsMap, ++rowIndex);
	        	        
	        for(DataQualityValidatorVO dataQualityValidatorVO : dataQualityValidatorVOs){
	        	
	            for (DataQualityValidatorColumnVO dataQualityValidatorColumnVO : dataQualityValidatorVO.getDataQualityValidatorColumnVOs()) {
	            	
	            	int cellIndex = 0;
	            	int totalOfRecords = dataQualityValidatorColumnVO.getTotalOfRecords();
	            	int totalOfInvalid = 0;
	            		            	
	            	if(dataQualityValidatorColumnVO.getDataQualityValidatorColumnRowVOs() != null){
	            		totalOfInvalid = dataQualityValidatorColumnVO.getDataQualityValidatorColumnRowVOs().size();
	            	}
	            	
	            	float percentualOfInvalid = (float) (totalOfInvalid * 100) / totalOfRecords;
	            	
	            	Row row = spreadsheet.createRow(++rowIndex);		
	            	row.createCell(cellIndex++).setCellValue(dataQualityValidatorVO.getSchema());
		            row.createCell(cellIndex++).setCellValue(dataQualityValidatorVO.getTable());
	            	row.createCell(cellIndex++).setCellValue(DaoUtil.getColumnFromColumnKey(dataQualityValidatorColumnVO.getColumn()));
	            	row.createCell(cellIndex++).setCellValue(totalOfRecords);
	            	row.createCell(cellIndex++).setCellValue(totalOfInvalid);
	            	row.createCell(cellIndex++).setCellValue(String.format("%.2f",percentualOfInvalid) + " %");
	            	row.createCell(cellIndex++).setCellValue(dataQualityValidatorColumnVO.getRegex());
				}
	        }

            FileOutputStream fos = new FileOutputStream(output);
            workbook.write(fos);
            fos.close();

            LOGGER.info("Excel file successfully written at " + output);
	        
		} catch(Exception exc){
			LOGGER.error("Could not export results to excel. Error[" + exc.getLocalizedMessage() + "].", exc);
			throw new TCCTechnicalException("An error occurred. Could not export results to excel.");
		}
	}
	
	private static void setExcelHeader(XSSFWorkbook workbook, XSSFSheet spreadsheet, int totalColumns, int rowNum, String headerText){
    	
    	XSSFRow header = spreadsheet.createRow(rowNum);
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        font.setFontName(FONT_NAME);
        font.setItalic(true);
        font.setColor(HSSFColor.BLUE_GREY.index);        
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        XSSFCell cell = header.createCell(0);
        cell.setCellValue(headerText);
        cell.setCellStyle(style); 
        spreadsheet.addMergedRegion(new CellRangeAddress(0,0,0,totalColumns));
    }
    
    private static void setColumnHeader(XSSFWorkbook workbook, XSSFSheet spreadsheet, Map<Integer, String> headersMap, int row){
    	
    	XSSFRow header = spreadsheet.createRow(row);
    	for (Map.Entry<Integer, String> entry : headersMap.entrySet()){
    		
    	    int cell = entry.getKey();
    	    String column = entry.getValue();
    		    	
	    	XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setFont(font);
	        XSSFCell cellHeader = header.createCell(cell);
	        cellHeader.setCellValue(column);
	        cellHeader.setCellStyle(style);
    	}
    }
}
