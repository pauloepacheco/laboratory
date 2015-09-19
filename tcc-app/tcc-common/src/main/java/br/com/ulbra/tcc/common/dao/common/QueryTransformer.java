package br.com.ulbra.tcc.common.dao.common;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.dao.constants.CommonConstants;
import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.response.DataQualityValidatorResponse;

/**
 * The QueryTransformer Class
 * 
 * @author Paulo Pacheco
 *
 */

@Component
public class QueryTransformer {
	
	public TableVO transformResultsIntoTableVO(Object result){
		
		int index = 0;		
		TableVO tableVO = new TableVO();		
		Object[] row = (Object[]) result;				
		
		tableVO.setTableName((String) row[index]);
		return tableVO;
	}
	
	public ColumnVO transformResultsIntoColumnVO(Object result){
		
		int index = 0;		
		ColumnVO columnVO = new ColumnVO();		
		Object[] row = (Object[]) result;				
		
		columnVO.setColumnName((String) row[index]);
		columnVO.setDataType((String) row[++index]);
		return columnVO;
	}
	
	public DataQualityValidatorResponse buildDataQualityValidatorResponse(List<Object> objectList, 
			String schemaName, String tableName, String sqlColumns){
		
		DataQualityValidatorResponse dataQualityValidatorResponse = new DataQualityValidatorResponse();
		final String columnsArr[] = sqlColumns.split(CommonConstants.COMMA);
		
		for (Object object : objectList) {
			if(object != null){
				
				final Object[] rowFromDB = (Object[]) object;				
				
				dataQualityValidatorResponse.setSchema(schemaName);
				dataQualityValidatorResponse.setTable(tableName);
				
				for(int i = 0; i<= columnsArr.length - 1; i++){
					
//					final String key = schemaName.concat(CommonConstants.COMMA).concat(tableName).
//							concat(CommonConstants.COMMA).concat(columnsArr[i]);
					
					//id,nome, email - sqlColumns
					//1,eduardo,pacheco@paulo.com - rowFromDB
					//2,pacheco,pacheco@paulo.com - rowFromDB
				}
			}
		}
		return dataQualityValidatorResponse;
	}
}
