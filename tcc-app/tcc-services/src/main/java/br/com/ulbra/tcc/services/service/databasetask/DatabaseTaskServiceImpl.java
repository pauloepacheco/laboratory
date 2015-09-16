package br.com.ulbra.tcc.services.service.databasetask;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulbra.tcc.common.dao.databasetask.DatabaseTaskDao;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableRequestWS;
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
	public List<SchemaVO> getInitialLoad() {		
		
		List<SchemaVO> schemaVOs = null;
		
		try{
			schemaVOs = databaseTaskDao.getSchemasFromDB();			
		} catch(DataAccessException dae){
			LOGGER.error("DataAccessException when getting tables from DB " + 
					dae.getMessage(),dae);
		}
		return schemaVOs;
	}

	public TableVO getColumnsFromTable(TableRequestWS tableRequest) {
		TableVO tableVO = null;
		
		try{
			tableVO = databaseTaskDao.getColumnsFromTable(tableRequest.getSchema(), tableRequest.getTable());
		} catch(DataAccessException dae){
			LOGGER.error("DataAccessException when getting columns from table " + 
					dae.getMessage(),dae);
		}
		return tableVO;
	}

	public void processDataQualityTask(List<TableVO> tableVO) {
		
		for (TableVO vo : tableVO) {
			if(vo != null){
				
			}
		}
		
	}
}
