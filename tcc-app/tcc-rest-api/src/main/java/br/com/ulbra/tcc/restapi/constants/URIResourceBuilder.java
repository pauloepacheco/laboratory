package br.com.ulbra.tcc.restapi.constants;

/**
 * The URI Constant Builder Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface URIResourceBuilder {

	public interface UserResource{
		
		public static final String USER_URI = "/ws/user";		
		public static final String GET_ALL_USERS_URI = "/getusers";	
	}
	
	public interface DataBaseTaskResource{
		
		public static final String DATA_BASE_URI  = "/ws/db";
		public static final String GET_DB_INFO_URI  = "/getInitialLoad";
		public static final String GET_COLUMNS_URI  = "/getColumns";
	}
}
