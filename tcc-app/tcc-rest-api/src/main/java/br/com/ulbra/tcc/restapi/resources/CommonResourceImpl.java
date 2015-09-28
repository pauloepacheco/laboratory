package br.com.ulbra.tcc.restapi.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.ulbra.tcc.common.exception.TCCWebServiceException;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.restapi.util.RegexUtil;

/**
 * The CommonResourceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Path(URIResourceBuilder.CommonResource.COMMON_URI)
public class CommonResourceImpl implements CommonResource {
	
	private static final Logger LOGGER = Logger.getLogger(CommonResourceImpl.class);

	@POST
	@Path(URIResourceBuilder.CommonResource.VALIDATOR_REGEX_URI)	
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegex(String regex) throws TCCWebServiceException {
		
		JSONObject json = new JSONObject();
		try {
			boolean result = RegexUtil.isRegexValid(regex);
			json.put("valid", result);
			
			LOGGER.info("Regex validator result[" + result + "] for regular " +  
					"expression[" + regex + "].");
			
		} catch (JSONException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception exc) {
			throw new TCCWebServiceException("Unexpected error ocorred when trying to validate regular expression.", exc);
		}
		return Response.ok().entity(json.toString()).build();
	}
}
