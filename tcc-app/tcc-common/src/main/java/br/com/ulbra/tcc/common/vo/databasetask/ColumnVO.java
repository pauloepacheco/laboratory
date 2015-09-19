package br.com.ulbra.tcc.common.vo.databasetask;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * The ColumnVO Class
 * 
 * @author Paulo Pacheco
 *
 */

@JsonPropertyOrder({ 
	"column", 
	"type",
	"regex"
})

public class ColumnVO {

	private String columnName;	
	private String dataType;
	private String regex;

	@JsonProperty("column")
	public String getColumnName() {
		return columnName;
	}

	@JsonProperty("column")
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@JsonProperty("type")
	public String getDataType() {
		return dataType;
	}

	@JsonProperty("type")
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@JsonProperty("regex")
	public String getRegex() {
		return regex;
	}

	@JsonProperty("regex")
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
