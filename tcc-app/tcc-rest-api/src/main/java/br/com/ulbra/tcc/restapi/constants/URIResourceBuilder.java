package br.com.ulbra.tcc.restapi.constants;

/**
 * The URI Constant Builder Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface URIResourceBuilder {

	public interface UserResource{
		
		static final String USER_URI = "/ws/user";		
		static final String GET_ALL_USERS_URI = "/getusers";	
	}
	
	public interface DataBaseTaskResource{
		
		static final String DATA_BASE_URI  = "/ws/db";
		static final String GET_DB_INFO_URI  = "/getInitialLoad";
		static final String GET_COLUMNS_URI  = "/getColumns";
		static final String PROCESS_DATA_QUALITY_REQUEST = "/dataquality";
	}
	
	public interface CommonResource{
		
		static final String COMMON_URI = "/ws/common";
		static final String VALIDATOR_REGEX_URI = "/validator/regex";
	}
}
