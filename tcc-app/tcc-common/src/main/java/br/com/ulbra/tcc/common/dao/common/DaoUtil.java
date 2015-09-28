package br.com.ulbra.tcc.common.dao.common;

import br.com.ulbra.tcc.common.constants.CommonConstants;

public class DaoUtil {

	public static String buildColumnKey(final String schema, final String table, final String column){
		return schema.concat(CommonConstants.DOT).concat(table).
				concat(CommonConstants.DOT).concat(column);
	}
	
	public static String getSchemaFromColumnKey(String key){
		return key.split(CommonConstants.ESCAPE_DOT)[0];
	}
	
	public static String getTableFromColumnKey(String key){
		return key.split(CommonConstants.ESCAPE_DOT)[1];
	}
	
	public static String getColumnFromColumnKey(String key){
		return key.split(CommonConstants.ESCAPE_DOT)[2];
	}
}
