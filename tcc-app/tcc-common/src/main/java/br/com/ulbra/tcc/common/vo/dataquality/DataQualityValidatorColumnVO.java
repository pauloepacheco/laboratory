package br.com.ulbra.tcc.common.vo.dataquality;

import java.util.List;

/**
 * The DataQualityValidatorColumnVO Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorColumnVO {

	private String column;	
	private List<DataQualityValidatorColumnRowVO> dataQualityValidatorColumnRowVOs;
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}	
	public List<DataQualityValidatorColumnRowVO> getDataQualityValidatorColumnRowVOs() {
		return dataQualityValidatorColumnRowVOs;
	}
	public void setDataQualityValidatorColumnRowVOs(
			List<DataQualityValidatorColumnRowVO> dataQualityValidatorColumnRowVOs) {
		this.dataQualityValidatorColumnRowVOs = dataQualityValidatorColumnRowVOs;
	}
}
