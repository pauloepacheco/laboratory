package br.com.ulbra.tcc.common.vo.dataquality;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The DataQualityValidatorVO Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorVO {
	
	private String schema;
	private String table;
	List<DataQualityValidatorColumnVO> dataQualityValidatorColumnVOs;
	
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

	@JsonProperty("columns")
	public List<DataQualityValidatorColumnVO> getDataQualityValidatorColumnVOs() {
		return dataQualityValidatorColumnVOs;
	}

	@JsonProperty("columns")
	public void setDataQualityValidatorColumnVOs(
			List<DataQualityValidatorColumnVO> dataQualityValidatorColumnVOs) {
		this.dataQualityValidatorColumnVOs = dataQualityValidatorColumnVOs;
	}
}
