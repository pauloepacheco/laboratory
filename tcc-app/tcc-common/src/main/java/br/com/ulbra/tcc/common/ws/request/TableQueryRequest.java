package br.com.ulbra.tcc.common.ws.request;

/**
 * The TableQueryRequest Class
 * 
 * @author Paulo Pacheco
 *
 */
public class TableQueryRequest {
	
	private String schema;
	private String table;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
