package br.com.ulbra.tcc.services.service.databasetask;

import java.util.List;


import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;

/**
 * The DatabaseTaskService Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface DatabaseTaskService {
	
	List<SchemaVO> getTablesAndColumnsFromDB();

}
