package br.com.ulbra.tcc.common.vo.table;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import br.com.ulbra.tcc.common.vo.column.ColumnVO;

@JsonPropertyOrder({ 
	"table", 
	"schema",
	"columns"
})
public class TableVO {
	
	private String tableName;
	private String tableSchema;	
	private List<ColumnVO> columnVOs;
	
	@JsonProperty("table")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@JsonProperty("schema")
	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	@JsonProperty("columns")
	public List<ColumnVO> getColumnVOs() {
		return columnVOs;
	}

	public void setColumnVOs(List<ColumnVO> columnVOs) {
		this.columnVOs = columnVOs;
	}
}
