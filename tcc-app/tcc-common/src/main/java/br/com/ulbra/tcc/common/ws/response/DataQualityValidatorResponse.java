package br.com.ulbra.tcc.common.ws.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The DataQualityValidatorResponse Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorResponse {
	
	private String schema;
	private String table;
	List<DataQualityValidatorColumnResponse> columnsResponse;
	
	@JsonProperty("schema")
	public String getSchema() {
		return schema;
	}
	
	@JsonProperty("schema")
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	@JsonProperty("table")
	public String getTable() {
		return table;
	}
	
	@JsonProperty("table")
	public void setTable(String table) {
		this.table = table;
	}

	@JsonProperty("column")
	public List<DataQualityValidatorColumnResponse> getColumnsResponse() {
		return columnsResponse;
	}

	@JsonProperty("column")
	public void setColumnsResponse(
			List<DataQualityValidatorColumnResponse> columnsResponse) {
		this.columnsResponse = columnsResponse;
	}
}
