package br.com.ulbra.tcc.common.exception;

/**
 * The TCCWebServiceException class 
 * 
 * @author Paulo Pacheco
 *
 */
public class TCCWebServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public TCCWebServiceException() {}

	public TCCWebServiceException(String customMessage) {
		super(customMessage);
	}

	public TCCWebServiceException(String customMessage, Throwable cause) {
		super(customMessage, cause);
	}
}
