package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskService;

/**
 * The DataBaseTaskResource Class
 * 
 * @author Paulo Pacheco
 *
 */
@Path(URIResourceBuilder.DataBaseTaskResource.DATA_BASE_URI)
public class DataBaseTaskResource {

	@GET
	@Path(URIResourceBuilder.DataBaseTaskResource.GET_DB_INFO_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDetails(){
		
		DatabaseTaskService tableService = ServiceLocator.
				getServiceInstance(ServiceBuilder.TABLE_SERVICE, DatabaseTaskService.class);
			
		List<SchemaVO> schemaVOList = tableService.getTablesAndColumnsFromDB();
		return Response.status(200).entity(schemaVOList).build();		
	}
}
