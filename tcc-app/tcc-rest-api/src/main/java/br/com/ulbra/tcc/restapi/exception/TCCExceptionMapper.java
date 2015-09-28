package br.com.ulbra.tcc.restapi.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.ulbra.tcc.common.exception.ErrorMessage;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;

/**
 * The TCCExceptionMapper Class
 * 
 * @author Paulo Pacheco
 *
 */
@Provider
public class TCCExceptionMapper implements ExceptionMapper<TCCWebServiceException> {

	public Response toResponse(TCCWebServiceException exception) {
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setDeveloperMessage(getStackTraceStringFromThrowable(exception));
		errorMessage.setMessage(exception.getMessage());
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).build();
	}
	
	private String getStackTraceStringFromThrowable(Exception ex){
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
