package br.com.ulbra.tcc.common.dao.common;
import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;

/**
 * The QueryTransformer Class
 * 
 * @author Paulo Pacheco
 *
 */

@Component
public class QueryTransformer {

	public SchemaVO transformResultsIntoSchemaVO(Object result){
		
		int index = 0;		
		SchemaVO schemaVO = new SchemaVO();		
		Object[] row = (Object[]) result;				
		
		schemaVO.setSchemaName((String) row[index]);
		return schemaVO;
	}
	
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
}
