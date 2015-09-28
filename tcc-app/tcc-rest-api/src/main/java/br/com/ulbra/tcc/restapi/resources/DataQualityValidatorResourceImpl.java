package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityReportVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.dataquality.DataQualityReportGenerator;
import br.com.ulbra.tcc.services.service.dataquality.DataQualityValidatorService;


/**
 * The DataQualityValidatorResourceImpl class
 * 
 * @author Paulo Pacheco
 *
 */
@Path(URIResourceBuilder.DataQualityValidatorResource.DATA_QUALITY_URI)
public class DataQualityValidatorResourceImpl extends SpringBeanAutowiringSupport implements DataQualityValidatorResource{

	private static final Logger LOGGER = Logger.getLogger(DataQualityValidatorResourceImpl.class);
	
	@POST
	@Path(URIResourceBuilder.DataQualityValidatorResource.DATA_QUALITY_REQUEST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(List<TableVO> tableVOs) throws TCCWebServiceException{
		
		List<DataQualityValidatorVO> dataQualityValidatorVOs = null;
		DataQualityReportVO dataQualityReportVO = null;
		
		final DataQualityValidatorService dataQualityValidatorService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATA_QUALITY_VALIDATOR, DataQualityValidatorService.class);
		
		final DataQualityReportGenerator dataQualityReportGeneratorService = ServiceLocator.
				getServiceInstance(ServiceBuilder.DATA_QUALITY_REPOR_GENERATOR, DataQualityReportGenerator.class);
		
		LOGGER.info("Data quality validation request initiated");
		try{
			dataQualityValidatorVOs = dataQualityValidatorService.performCustomValidations(tableVOs);
			dataQualityReportVO = dataQualityReportGeneratorService.startReportGeneration(dataQualityValidatorVOs);
			
		} catch(TCCTechnicalException tte){
			throw new TCCWebServiceException(tte.getMessage(), tte);
			
		} catch (TCCBusinessException tbe) {
			throw new TCCWebServiceException(tbe.getMessage(), tbe);
		}
		return Response.status(200).entity(dataQualityReportVO).build();
	}
}
