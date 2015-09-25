package br.com.ulbra.tcc.common.dao.common;

import br.com.ulbra.tcc.common.constants.CommonConstants;

public class DaoUtil {

	public static String buildColumnKey(final String schema, final String table, final String column){
		return schema.concat(CommonConstants.DOT).concat(table).
				concat(CommonConstants.DOT).concat(column);
	}
}
