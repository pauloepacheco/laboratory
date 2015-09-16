package br.com.ulbra.tcc.restapi.resources;

import javax.ws.rs.core.Response;

/**
 * The CommonResource Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface CommonResource {

	public Response validateRegex(String regex);
}
