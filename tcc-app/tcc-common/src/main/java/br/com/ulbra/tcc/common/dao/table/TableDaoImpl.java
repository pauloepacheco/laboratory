package br.com.ulbra.tcc.common.dao.table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.common.dao.common.AbstractDao;
import br.com.ulbra.tcc.common.dao.common.QueryTransformer;
import br.com.ulbra.tcc.common.dao.constants.DaoConstants;
import br.com.ulbra.tcc.common.vo.column.ColumnVO;
import br.com.ulbra.tcc.common.vo.table.TableVO;

/**
 * The Table Dao Class
 * 
 * @author Paulo Pacheco
 *
 */

@Repository
public class TableDaoImpl extends AbstractDao<Object, BigDecimal> implements TableDao{

	private static final String GET_TABLES_QUERY = "SELECT table_name, table_schema " +
			"FROM information_schema.tables WHERE table_schema = ?;";
	
	private static final String GET_COLUMNS_FROM_TABLE_QUERY = "SELECT column_name, data_type " + 
			"FROM information_schema.columns WHERE table_name = ? AND table_schema = ?";
	
	@Autowired
	private transient QueryTransformer queryTransformer;
	
	public List<TableVO> getTablesFromDB() throws DataAccessException {
		
		final Query query = entityManager.createNativeQuery(GET_TABLES_QUERY);
		
		int index = 0;
		List<TableVO> tableVOs = null;
		
		query.setParameter(++index, DaoConstants.DEFAULT_SCHEMA);
		
		final List<Object> resultList = query.getResultList();
		
		if(resultList != null && !resultList.isEmpty()){
			
			tableVOs = new ArrayList<TableVO>();
			
			TableVO tableVO = new TableVO();
			
			for (Object result : resultList) {
				
				tableVO = queryTransformer.
						transformResultsIntoTableVO(result);
				
				List<ColumnVO> columnVOs = getColumnsFromTable(
						tableVO.getTableName());
				
				tableVO.setColumnVOs(columnVOs);
				tableVOs.add(tableVO);
			}
		}	
		return tableVOs;
	}

	public List<ColumnVO> getColumnsFromTable(final String tableName) 
			throws DataAccessException {
		
		int index = 0;
		List<ColumnVO> columnVOs = null;
		
		final Query query = entityManager.createNativeQuery(GET_COLUMNS_FROM_TABLE_QUERY);
		query.setParameter(++index, tableName);
		query.setParameter(++index, DaoConstants.DEFAULT_SCHEMA);

		final List<Object> resultList = query.getResultList();
		
		if(resultList != null && !resultList.isEmpty()){
			
			columnVOs = new ArrayList<ColumnVO>();
			
			for (Object result : resultList) {
				columnVOs.add(queryTransformer.
						transformResultsIntoColumnVO(result));
			}
		}
		
		return columnVOs;
	}	
}
