package br.com.ulbra.tcc.restapi.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * The JSONUtil class
 * 
 * @author Paulo Eduardo
 */
public class JSONUtil {

	private static ObjectMapper MAPPER = new ObjectMapper();
	
	public static <T> T convertJsonToJava(Class<T> clazz, String json)
		
			throws JsonParseException, JsonMappingException, IOException{
				
		return MAPPER.readValue(json, clazz);
	}
	
	public static String convertJavaToJson(Object object) 
			throws JsonGenerationException, JsonMappingException, IOException{
		
		return MAPPER.writeValueAsString(object);
	}
}
