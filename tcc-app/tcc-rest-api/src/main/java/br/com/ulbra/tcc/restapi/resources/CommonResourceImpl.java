package br.com.ulbra.tcc.restapi.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

	@POST
	@Path(URIResourceBuilder.CommonResource.VALIDATOR_REGEX_URI)	
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegex(String regex) {
		JSONObject json = new JSONObject();
		try {
			json.put("valid", RegexUtil.isRegexValid(regex));
		} catch (JSONException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.ok().entity(json.toString()).build();
	}
}
