package br.com.ulbra.tcc.common.vo.dataquality;

import java.util.List;

/**
 * The ColumnRegexVO Class
 * 
 * @author Paulo Pacheco
 *
 */
public class ColumnRegexVO {

	private String sqlColumns;
	private List<String> regex;
	
	public String getSqlColumns() {
		return sqlColumns;
	}
	public void setSqlColumns(String sqlColumns) {
		this.sqlColumns = sqlColumns;
	}
	public List<String> getRegex() {
		return regex;
	}
	public void setRegex(List<String> regex) {
		this.regex = regex;
	}
}
