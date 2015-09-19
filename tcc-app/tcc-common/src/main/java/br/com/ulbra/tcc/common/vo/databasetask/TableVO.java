package br.com.ulbra.tcc.common.vo.databasetask;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * The TableVO Class
 * 
 * @author Paulo Pacheco
 *
 */

@JsonPropertyOrder({ 
	"schema", 
	"table", 
	"columns"
})

public class TableVO {
	
	private String schema;
	private String tableName;
	private List<ColumnVO> columnVOs;
	
	@JsonProperty("schema")
	public String getSchema() {
		return schema;
	}

	@JsonProperty("schema")
	public void setSchema(String schema) {
		this.schema = schema;
	}

	@JsonProperty("table")
	public String getTableName() {
		return tableName;
	}

	@JsonProperty("table")
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@JsonProperty("columns")
	public List<ColumnVO> getColumnVOs() {
		return columnVOs;
	}

	@JsonProperty("columns")
	public void setColumnVOs(List<ColumnVO> columnVOs) {
		this.columnVOs = columnVOs;
	}
}
