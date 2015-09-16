package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableRequestWS;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskService;

/**
 * The DataBaseTaskResourceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Path(URIResourceBuilder.DataBaseTaskResource.DATA_BASE_URI)
public class DataBaseTaskResourceImpl implements DataBaseTaskResource{

	@POST
	@Path(URIResourceBuilder.DataBaseTaskResource.GET_DB_INFO_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDetails(){
		
		final DatabaseTaskService dbTaskService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATABASE_TASK_SERVICE, DatabaseTaskService.class);
			
		List<SchemaVO> schemaVOList = dbTaskService.getInitialLoad();
		return Response.status(200).entity(schemaVOList).build();		
	}
	
	@POST
	@Path(URIResourceBuilder.DataBaseTaskResource.GET_COLUMNS_URI)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumns(TableRequestWS requestWS) {

		final DatabaseTaskService dbTaskService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATABASE_TASK_SERVICE, DatabaseTaskService.class);
		
		TableVO tableVO = null;
		
		if(requestWS != null){
			tableVO = dbTaskService.getColumnsFromTable(requestWS);
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.status(200).entity(tableVO).build();
	}
	
	@POST
	@Path(URIResourceBuilder.DataBaseTaskResource.PROCESS_DATA_QUALITY_REQUEST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(List<TableVO> request){
				
		final DatabaseTaskService dbTaskService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATABASE_TASK_SERVICE, DatabaseTaskService.class);
		
		dbTaskService.processDataQualityTask(request);
		
		return null;
		
	}
}
