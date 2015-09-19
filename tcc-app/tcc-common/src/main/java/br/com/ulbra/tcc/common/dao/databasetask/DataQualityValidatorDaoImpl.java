package br.com.ulbra.tcc.common.dao.databasetask;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.common.dao.common.AbstractDao;

@Repository
public class DataQualityValidatorDaoImpl extends AbstractDao<Object, BigDecimal> implements DataQualityValidatorDao {

	public List<Object> processDataQualityRequest(final String sql) throws DataAccessException {
		
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
}
