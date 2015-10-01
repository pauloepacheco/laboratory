package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.request.TableQueryRequest;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder.DataBaseTaskResource;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskService;

/**
 * The DataBaseTaskResourceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Path(DataBaseTaskResource.DATA_BASE_URI)
public class DataBaseTaskResourceImpl implements DataBaseTaskResource{

	private static final Logger LOGGER = Logger.getLogger(DataBaseTaskResourceImpl.class);
	
	@POST
	@Path(DataBaseTaskResource.GET_DB_INFO_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTableDetails() throws TCCWebServiceException{
		
		final DatabaseTaskService dbTaskService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATABASE_TASK_SERVICE, DatabaseTaskService.class);
		
		List<SchemaVO> schemaVOList = null;
		LOGGER.info("Getting table details to start application");
			
		try{
			schemaVOList = dbTaskService.getInitialLoad();
			
		} catch(TCCBusinessException tbe){
			throw new TCCWebServiceException(tbe.getMessage(), tbe);
			
		} catch (TCCTechnicalException tte) {
			throw new TCCWebServiceException(tte.getMessage(), tte);
		}
		return Response.status(200).entity(schemaVOList).build();		
	}
	
	@POST
	@Path(DataBaseTaskResource.GET_COLUMNS_URI)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getColumns(TableQueryRequest requestWS) throws TCCWebServiceException {

		final DatabaseTaskService dbTaskService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATABASE_TASK_SERVICE, DatabaseTaskService.class);
		
		TableVO tableVO = null;
		
		if(requestWS != null){
			LOGGER.debug("Getting column for table[" + requestWS.getTable() + "].");
			try{
				tableVO = dbTaskService.getColumnsFromTable(requestWS);
			} catch(TCCBusinessException tbe){
				throw new TCCWebServiceException(tbe.getMessage());
			} catch (TCCTechnicalException tte) {
				throw new TCCWebServiceException(tte.getMessage());
			}
		} else {
			LOGGER.debug("Parameters to get columns for table are invalid.");
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.status(200).entity(tableVO).build();
	}
}
