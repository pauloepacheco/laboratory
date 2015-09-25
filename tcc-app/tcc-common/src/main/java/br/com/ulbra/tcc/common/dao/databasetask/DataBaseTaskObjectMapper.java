package br.com.ulbra.tcc.common.dao.databasetask;

import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;

/**
 * The DataBaseTaskObjectMapper Class
 * 
 * @author Paulo Pacheco
 *
 */

@Component
public class DataBaseTaskObjectMapper {
	
	public TableVO buildTableVO(Object result){
		
		int index = 0;		
		TableVO tableVO = new TableVO();		
		Object[] row = (Object[]) result;				
		
		tableVO.setTableName((String) row[index]);
		return tableVO;
	}
	
	public ColumnVO buildColumnVO(Object result){
		
		int index = 0;		
		ColumnVO columnVO = new ColumnVO();		
		Object[] row = (Object[]) result;				
		
		columnVO.setColumnName((String) row[index]);
		columnVO.setDataType((String) row[++index]);
		return columnVO;
	}
}
