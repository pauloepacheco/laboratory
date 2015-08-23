package br.com.ulbra.tcc.restapi.resources;

import javax.ws.rs.core.Response;

import br.com.ulbra.tcc.common.ws.request.TableRequestWS;

/**
 * The DataBaseTaskResource Interface
 * 
 * @author Paulo Pacheco
 *
 */
public interface DataBaseTaskResource {

	public Response getTableDetails();
	
	public Response getColumns(TableRequestWS requestWS);
}
