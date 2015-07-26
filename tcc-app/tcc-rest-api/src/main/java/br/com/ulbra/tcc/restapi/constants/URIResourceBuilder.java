package br.com.ulbra.tcc.restapi.constants;

/**
 * The URI Constant Builder Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface URIResourceBuilder {

	public interface UserResource{
		
		public static final String USER_URI = "/user";		
		public static final String GET_ALL_USERS_URI = "/getusers";	
	}
	
	public interface DataBaseResource{
		
		public static final String DATA_BASE_URI  = "/db";
		public static final String GET_TABLES_URI  = "/gettables";	
	}
}
