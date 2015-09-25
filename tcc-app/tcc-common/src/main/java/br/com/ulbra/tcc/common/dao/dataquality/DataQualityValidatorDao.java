package br.com.ulbra.tcc.common.dao.dataquality;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * The DataQualityValidatorDao Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface DataQualityValidatorDao {
	
	public List<Object> processDataQualityRequest(final String sql) throws DataAccessException;
	
	public String getPrimaryKeyColumnNameFromTable(final String schema, final String table) throws DataAccessException;
}
