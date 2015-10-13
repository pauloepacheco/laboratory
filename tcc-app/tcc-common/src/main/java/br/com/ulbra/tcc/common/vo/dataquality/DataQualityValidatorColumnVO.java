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
	private int totalOfRecords;
	private String regex;
	private List<DataQualityValidatorColumnRowVO> dataQualityValidatorColumnRowVOs;
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public int getTotalOfRecords() {
		return totalOfRecords;
	}
	public void setTotalOfRecords(int totalOfRecords) {
		this.totalOfRecords = totalOfRecords;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public List<DataQualityValidatorColumnRowVO> getDataQualityValidatorColumnRowVOs() {
		return dataQualityValidatorColumnRowVOs;
	}
	public void setDataQualityValidatorColumnRowVOs(
			List<DataQualityValidatorColumnRowVO> dataQualityValidatorColumnRowVOs) {
		this.dataQualityValidatorColumnRowVOs = dataQualityValidatorColumnRowVOs;
	}
}