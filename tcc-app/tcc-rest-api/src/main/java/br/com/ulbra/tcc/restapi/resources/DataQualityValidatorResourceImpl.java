package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.dataquality.DataQualityValidatorService;


/**
 * The DataQualityValidatorResourceImpl class
 * 
 * @author Paulo Pacheco
 *
 */
@Path(URIResourceBuilder.DataQualityValidatorResource.DATA_QUALITY_URI)
public class DataQualityValidatorResourceImpl implements DataQualityValidatorResource{

	private static final Logger LOGGER = Logger.getLogger(DataQualityValidatorResourceImpl.class);
	
	@POST
	@Path(URIResourceBuilder.DataQualityValidatorResource.DATA_QUALITY_REQUEST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(List<TableVO> request){
		
		final DataQualityValidatorService dataQualityValidatorService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATA_QUALITY_VALIDATOR, DataQualityValidatorService.class);
		
		LOGGER.info("Data quality validation request initiated");
		
		dataQualityValidatorService.processDataQualityRequest(request);
		return null;		
	}
}
