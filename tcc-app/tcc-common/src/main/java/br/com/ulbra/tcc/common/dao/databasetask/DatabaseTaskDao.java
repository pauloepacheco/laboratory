package br.com.ulbra.tcc.common.dao.databasetask;

import java.util.List;


import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;

/**
 * The DatabaseTaskDao Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface DatabaseTaskDao {

	List<SchemaVO> getSchemasFromDB() throws DataAccessException;
	
	List<TableVO> getTablesFromSchema(String schemaName) throws DataAccessException;
	
	TableVO getColumnsFromTable(String schemaName, String tableName) throws DataAccessException;
}
