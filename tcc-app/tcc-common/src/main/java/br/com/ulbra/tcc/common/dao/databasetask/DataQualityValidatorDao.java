package br.com.ulbra.tcc.common.dao.databasetask;

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
}
