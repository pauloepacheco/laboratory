package br.com.ulbra.tcc.services.service.databasetask;

import java.util.List;

import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableRequestWS;

/**
 * The DatabaseTaskService Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface DatabaseTaskService {
	
	List<SchemaVO> getInitialLoad();
	
	TableVO getColumnsFromTable(TableRequestWS tableRequest);

}
