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
	"table", 
	"columns"
})

public class TableVO {
	
	private String tableName;
	private List<ColumnVO> columnVOs;
	
	@JsonProperty("table")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@JsonProperty("columns")
	public List<ColumnVO> getColumnVOs() {
		return columnVOs;
	}

	public void setColumnVOs(List<ColumnVO> columnVOs) {
		this.columnVOs = columnVOs;
	}
}
