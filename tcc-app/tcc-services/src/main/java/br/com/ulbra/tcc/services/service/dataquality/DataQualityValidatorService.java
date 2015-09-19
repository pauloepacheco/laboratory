package br.com.ulbra.tcc.services.service.dataquality;

import java.util.List;

import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.response.DataQualityValidatorResponse;

/**
 * The DataQualityValidatorService Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface DataQualityValidatorService {

	public DataQualityValidatorResponse processDataQualityRequest(List<TableVO> tableVOList);
}
