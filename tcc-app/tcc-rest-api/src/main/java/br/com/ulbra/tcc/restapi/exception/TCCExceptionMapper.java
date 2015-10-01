package br.com.ulbra.tcc.restapi.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import br.com.ulbra.tcc.common.exception.ErrorMessage;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;
import br.com.ulbra.tcc.restapi.constants.WSConstants.WSException;

/**
 * The TCCExceptionMapper Class
 * 
 * @author Paulo Pacheco
 *
 */
@Provider
public class TCCExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = Logger.getLogger(TCCExceptionMapper.class);
	
	public Response toResponse(Exception exception) {
		
		LOGGER.error("An error throwed from REST API", exception);
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setDeveloperMessage(getStackTraceStringFromThrowable(exception));
		
		if(exception instanceof TCCWebServiceException){
			errorMessage.setMessage(exception.getMessage());
		} else {
			errorMessage.setMessage(WSException.TECHNICAL_ERROR);
		}
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).build();
	}
	
	private String getStackTraceStringFromThrowable(Exception ex){
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
