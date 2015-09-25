package br.com.ulbra.tcc.common.vo.dataquality;

import java.util.List;

/**
 * The DataQualityValidatorColumnRowVO Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorColumnRowVO {

	private String row;
	private List<Character> failedChars;
	
	public String getRow() {
		return row;
	}
	
	public void setRow(String row) {
		this.row = row;
	}
	
	public List<Character> getFailedChars() {
		return failedChars;
	}
	
	public void setFailedChars(List<Character> failedChars) {
		this.failedChars = failedChars;
	}
}
