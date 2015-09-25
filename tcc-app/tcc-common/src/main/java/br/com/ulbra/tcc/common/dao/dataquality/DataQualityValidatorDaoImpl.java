package br.com.ulbra.tcc.common.dao.dataquality;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.common.constants.CommonConstants.PostgressSchema;
import br.com.ulbra.tcc.common.dao.common.AbstractDao;

@Repository
public class DataQualityValidatorDaoImpl extends AbstractDao<Object, BigDecimal> implements DataQualityValidatorDao {

	private static final String PRIMARY_KEY_COLUMN_QUERY = "SELECT c.column_name "
			+ "FROM information_schema.table_constraints tc  JOIN information_schema.constraint_column_usage " 
			+ "AS ccu USING (constraint_schema, constraint_name) JOIN information_schema.columns AS c ON " 
			+ "c.table_schema = ? AND tc.table_name = c.table_name AND ccu.column_name = " 
			+ "c.column_name where constraint_type = ? and tc.table_name = ?;";
	
	public List<Object> processDataQualityRequest(final String sql) throws DataAccessException {
		
		Query query = entityManager.createNativeQuery(sql);
		return (List<Object>) query.getResultList();
	}
	
	public String getPrimaryKeyColumnNameFromTable(final String schema,  final String table){
		
		int index = 0;
		final Query query = entityManager.createNativeQuery(PRIMARY_KEY_COLUMN_QUERY);		
		query.setParameter(++index, schema);
		query.setParameter(++index, PostgressSchema.PK);
		query.setParameter(++index, table);
		List<Object> result = (List<Object>) query.getResultList();
		
		if(result != null){
			return (String) result.get(0);
		}
		return null;
	}
}
