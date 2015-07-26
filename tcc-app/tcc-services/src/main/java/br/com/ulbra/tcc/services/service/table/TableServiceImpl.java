package br.com.ulbra.tcc.services.service.table;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulbra.tcc.common.dao.table.TableDao;
import br.com.ulbra.tcc.common.vo.table.TableVO;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;

/**
 * The Table Service Implementation Class
 * 
 * @author Paulo Pacheco
 *
 */

@Service(ServiceBuilder.TABLE_SERVICE)
public class TableServiceImpl implements TableService {
	
	private static final Logger LOGGER = Logger.getLogger(TableServiceImpl.class);
	
	@Autowired
	private transient TableDao tableDao;

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<TableVO> getTablesAndColumnsFromDB() {		
		
		List<TableVO> tableVOs = null;
		
		try{
			tableVOs = tableDao.getTablesFromDB();			
		} catch(DataAccessException dae){
			LOGGER.error("DataAccessException when getting tables from DB " + 
					dae.getMessage(),dae);
		}
		return tableVOs;
	}
}
