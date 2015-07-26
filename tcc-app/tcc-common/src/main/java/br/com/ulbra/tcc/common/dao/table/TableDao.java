package br.com.ulbra.tcc.common.dao.table;

import java.util.List;

import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.vo.column.ColumnVO;
import br.com.ulbra.tcc.common.vo.table.TableVO;

public interface TableDao {

	List<TableVO> getTablesFromDB() throws DataAccessException;
	
	List<ColumnVO> getColumnsFromTable(String tableName) throws DataAccessException;
}
