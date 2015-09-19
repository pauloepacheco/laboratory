package br.com.ulbra.tcc.common.ws.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The DataQualityValidatorColumnResponse Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorColumnResponse {

	private String columnName;
	private List<Object> rows;
	
	@JsonProperty("column")
	public String getColumnName() {
		return columnName;
	}
	
	@JsonProperty("column")
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	@JsonProperty("rows")
	public List<Object> getRows() {
		return rows;
	}
	
	@JsonProperty("rows")
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
}
