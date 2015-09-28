package br.com.ulbra.tcc.restapi.resources;

import javax.ws.rs.core.Response;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;

/**
 * The CommonResource Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface CommonResource {

	public Response validateRegex(String regex) throws TCCWebServiceException;
}
