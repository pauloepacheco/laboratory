package br.com.ulbra.tcc.services.service.dataquality;

import java.util.List;

import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;

/**
 * The DataQualityValidatorService Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface DataQualityValidatorService {

	public List<DataQualityValidatorVO> performCustomValidations(List<TableVO> tableVOs);
	
}
