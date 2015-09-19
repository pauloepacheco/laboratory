package br.com.ulbra.tcc.common.dao.databasetask;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.common.dao.common.AbstractDao;
import br.com.ulbra.tcc.common.dao.common.QueryTransformer;
import br.com.ulbra.tcc.common.dao.constants.CommonConstants;
import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;

/**
 * The DatabaseTaskDaoImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Repository
public class DatabaseTaskDaoImpl extends AbstractDao<Object, BigDecimal> implements DatabaseTaskDao, CommonConstants{

	private static final String GET_SCHEMAS_QUERY = "SELECT table_schema " + 
			"FROM information_schema.tables WHERE table_schema not in (?, ?);";
	
	private static final String GET_TABLES_QUERY = "SELECT table_name, table_schema " +
			"FROM information_schema.tables WHERE table_schema = ?;";
	
	private static final String GET_COLUMNS_FROM_TABLE_QUERY = "SELECT column_name, data_type " + 
			"FROM information_schema.columns WHERE table_name = ? AND table_schema = ?";
	
	@Autowired
	private transient QueryTransformer queryTransformer;
	
	public List<SchemaVO> getSchemasFromDB() throws DataAccessException {

		final Query query = entityManager.createNativeQuery(GET_SCHEMAS_QUERY);
		int index = 0;
		List<SchemaVO> schemaVOs = null;
		
		query.setParameter(++index, PostgressSchema.PG_CATALOG_SCHEMA);
		query.setParameter(++index, PostgressSchema.PG_INFORMATION_SCHEMA);
		

		final Set<String> resultList = new HashSet<String>(query.getResultList());
		
		if(resultList != null && !resultList.isEmpty()){
			
			schemaVOs = new ArrayList<SchemaVO>();
			
			for (String schemaName : resultList) {
				
				SchemaVO schemaVO = new SchemaVO(schemaName);				
				List<TableVO> tableVOs = getTablesFromSchema(schemaName);
				
				schemaVO.setTableVOs(tableVOs);
				schemaVOs.add(schemaVO);
			}
		}
		
		return schemaVOs;
	}	
	
	public List<TableVO> getTablesFromSchema(final String schemaName) throws DataAccessException {
		
		final Query query = entityManager.createNativeQuery(GET_TABLES_QUERY);
		
		int index = 0;
		List<TableVO> tableVOs = null;
		
		query.setParameter(++index, schemaName);
		
		final List<Object> resultList = query.getResultList();
		
		if(resultList != null && !resultList.isEmpty()){
			
			tableVOs = new ArrayList<TableVO>();
			
			TableVO tableVO = new TableVO();
			
			for (Object result : resultList) {
				
				tableVO = queryTransformer.
						transformResultsIntoTableVO(result);
				
				tableVOs.add(tableVO);
			}
		}	
		return tableVOs;
	}

	public TableVO getColumnsFromTable(final String schemaName, 
			final String tableName) throws DataAccessException {
		
		int index = 0;
		List<ColumnVO> columnVOs = null;
		TableVO tableVO = null;
		
		final Query query = entityManager.createNativeQuery(GET_COLUMNS_FROM_TABLE_QUERY);
		query.setParameter(++index, tableName);
		query.setParameter(++index, schemaName);

		final List<Object> resultList = query.getResultList();
		
		if(resultList != null && !resultList.isEmpty()){
			
			tableVO = new TableVO();
			tableVO.setTableName(tableName);
			tableVO.setSchema(schemaName);
			columnVOs = new ArrayList<ColumnVO>();
			
			for (Object result : resultList) {
				columnVOs.add(queryTransformer.
						transformResultsIntoColumnVO(result));
			}
			
			tableVO.setColumnVOs(columnVOs);
		}
		
		return tableVO;
	}
}
