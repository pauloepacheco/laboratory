package br.com.ulbra.tcc.services.service.databasetask;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulbra.tcc.common.dao.databasetask.DatabaseTaskDao;
import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableQueryRequest;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;

/**
 * The DatabaseTaskServiceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Service(ServiceBuilder.DATABASE_TASK_SERVICE)
public class DatabaseTaskServiceImpl implements DatabaseTaskService {
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseTaskServiceImpl.class);
	
	@Autowired
	private transient DatabaseTaskDao databaseTaskDao;

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<SchemaVO> getInitialLoad() throws TCCTechnicalException, TCCBusinessException {		
		
		List<SchemaVO> schemaVOs = null;
		
		try{
			schemaVOs = databaseTaskDao.getSchemasFromDB();
			
		} catch(DataAccessException dae){			
			LOGGER.error("DataAccessException when getting tables from DB[" + dae.getMessage() + "].",dae);
			throw new TCCTechnicalException("An error ocorred when trying to get tables from database.", dae);
			
		} catch (Exception exc) {
			LOGGER.error("Exception when getting tables from DB[" + exc.getMessage() + "].",exc);
			throw new TCCTechnicalException("Unexpected error ocorred when trying to get tables from database.", exc);
		}
		return schemaVOs;
	}

	public TableVO getColumnsFromTable(TableQueryRequest tableRequest) throws TCCTechnicalException, TCCBusinessException {
		
		TableVO tableVO = null;
		try{
			tableVO = databaseTaskDao.getColumnsFromTable(tableRequest.getSchema(), tableRequest.getTable());
			
		} catch(DataAccessException dae){
			LOGGER.error("DataAccessException when getting columns from table[" + dae.getMessage() + "].",dae);
			throw new TCCTechnicalException("An error ocorred when trying to get columns for table.", dae);
		} catch (Exception exc) {
			LOGGER.error("Exception when getting columns from table[" + exc.getMessage() + "].",exc);
			throw new TCCTechnicalException("Unexpected error ocorred when trying to get columns for table.", exc);
		}
		return tableVO;
	}
}
