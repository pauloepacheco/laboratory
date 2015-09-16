package br.com.ulbra.tcc.common.vo.databasetask;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * The SchemaVO Class
 * 
 * @author Paulo Pacheco
 *
 */

@JsonPropertyOrder({  
	"schema",
	"tables"
})

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SchemaVO {

	public SchemaVO() {}
	
	public SchemaVO(String schemaName) {
		super();
		this.schemaName = schemaName;
	}

	private String schemaName;
	private List<TableVO> tableVOs;
	
	@JsonProperty("schema")
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	@JsonProperty("tables")
	public List<TableVO> getTableVOs() {
		return tableVOs;
	}
	public void setTableVOs(List<TableVO> tableVOs) {
		this.tableVOs = tableVOs;
	}
}

