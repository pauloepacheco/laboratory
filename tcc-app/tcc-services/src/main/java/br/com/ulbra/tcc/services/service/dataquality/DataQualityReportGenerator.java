package br.com.ulbra.tcc.services.service.dataquality;

import java.util.List;

import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityReportVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;

public interface DataQualityReportGenerator {

	public DataQualityReportVO startReportGeneration(final List<DataQualityValidatorVO> dataQualityValidatorVOs) 
			throws TCCTechnicalException, TCCBusinessException;
	
	public String getDownloadReportPath(String id) throws TCCTechnicalException, TCCBusinessException;
}
