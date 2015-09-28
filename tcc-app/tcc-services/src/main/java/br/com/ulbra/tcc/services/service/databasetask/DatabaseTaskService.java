package br.com.ulbra.tcc.services.service.databasetask;

import java.util.List;

import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableQueryRequest;

/**
 * The DatabaseTaskService Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface DatabaseTaskService {
	
	public List<SchemaVO> getInitialLoad() throws TCCTechnicalException, TCCBusinessException;
	public TableVO getColumnsFromTable(TableQueryRequest tableRequest) throws TCCTechnicalException, TCCBusinessException;
}
