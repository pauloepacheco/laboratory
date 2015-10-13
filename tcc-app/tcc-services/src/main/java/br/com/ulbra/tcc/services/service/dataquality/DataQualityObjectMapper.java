package br.com.ulbra.tcc.services.service.dataquality;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.constants.CommonConstants;
import br.com.ulbra.tcc.common.dao.common.DaoUtil;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorColumnRowVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorColumnVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;
import br.com.ulbra.tcc.services.util.ServiceUtil;

/**
 * The DataQualityObjectMapper Class
 * 
 * @author Paulo Pacheco
 *
 */
@Component
public class DataQualityObjectMapper {
	
	@SuppressWarnings("unchecked")
	public DataQualityValidatorVO buildDataQualityValidatorVO(final Map<String, Object> parameters){
		
		final String schema = String.valueOf(parameters.get("schema"));
		final String table = String.valueOf(parameters.get("table"));
		final List<String> regexByColumn = (List<String>) parameters.get("regexByColumn");
		final String regexArr[] = regexByColumn.toArray(new String[regexByColumn.size()]);
		
		final Map<String, List<String>> columnRowMap = buildRowsByColumnMap(parameters);
		
		int columnIndex = 0;		
		List<DataQualityValidatorColumnVO> dataQualityValidatorColumnVOs = new ArrayList<DataQualityValidatorColumnVO>();
		DataQualityValidatorColumnVO dataQualityValidatorColumnVO = null;
		DataQualityValidatorColumnRowVO dataQualityValidatorColumnRowVO = null;
		List<DataQualityValidatorColumnRowVO> dataQualityValidatorColumnRowVOs = null;		
		
		//goes thru all selected columns		
		for (Map.Entry<String, List<String>> entry : columnRowMap.entrySet()){
			
			final String regex = regexArr[columnIndex];
			dataQualityValidatorColumnRowVOs = new ArrayList<DataQualityValidatorColumnRowVO>();

			int numberOfRecords = 0;
			
			//goes thru all records for a given column
			for (String row : entry.getValue()) { //records from db
				
				numberOfRecords++;
				List<Character> failedChars = ServiceUtil.getFailedRegexChars(regex, row);
				
				//needs to add only the failed records
				if(!failedChars.isEmpty()){
					dataQualityValidatorColumnRowVO = new DataQualityValidatorColumnRowVO();
					dataQualityValidatorColumnRowVO.setFailedChars(failedChars);
					dataQualityValidatorColumnRowVO.setRow(row);
					dataQualityValidatorColumnRowVOs.add(dataQualityValidatorColumnRowVO);
				}
			}
			
			dataQualityValidatorColumnVO = new DataQualityValidatorColumnVO();
			dataQualityValidatorColumnVO.setColumn(entry.getKey());
			dataQualityValidatorColumnVO.setDataQualityValidatorColumnRowVOs(dataQualityValidatorColumnRowVOs);
			dataQualityValidatorColumnVO.setTotalOfRecords(numberOfRecords);
			dataQualityValidatorColumnVO.setRegex(regex);
			dataQualityValidatorColumnVOs.add(dataQualityValidatorColumnVO);
			
			columnIndex ++;
		}
		
		DataQualityValidatorVO dataQualityValidatorVO = new DataQualityValidatorVO();
		dataQualityValidatorVO.setTable(table);
		dataQualityValidatorVO.setSchema(schema);
		dataQualityValidatorVO.setDataQualityValidatorColumnVOs(dataQualityValidatorColumnVOs);
		return dataQualityValidatorVO;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, List<String>> buildRowsByColumnMap(final Map<String, Object> parameters){
		
		String schema = String.valueOf(parameters.get("schema"));
		String table = String.valueOf(parameters.get("table"));
		String sqlColumns = String.valueOf(parameters.get("columns"));
		List<Object> rows = (List<Object>) parameters.get("rows");
		
		final String columnsArr[] = sqlColumns.split(CommonConstants.COMMA);
		
		Object[] rowFromDB = new Object[columnsArr.length];
		Map<String, List<String>> columnRowMap = new LinkedHashMap<String, List<String>>();
		
		for (Object object : rows) {
			
			if(object instanceof Object[]){
				rowFromDB = (Object[]) object;
			} else {
				rowFromDB[0] = object;
			}
		
			List<String> tempRow = null;
			
			for(int i = 0; i<= columnsArr.length - 1; i++){
				
				if(rowFromDB[i] == null){
					continue;
				}
				
				tempRow = new ArrayList<String>();				
				final String key = DaoUtil.buildColumnKey(schema, table, columnsArr[i]);
				final String columnData = String.valueOf(rowFromDB[i]);
				
				if(columnRowMap.get(key) != null){
					tempRow.addAll(columnRowMap.get(key));
				}				
				tempRow.add(columnData);
				columnRowMap.put(key, tempRow);
			}
		}
		return columnRowMap;
	}
}
