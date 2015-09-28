package br.com.ulbra.tcc.common.exception;

/**
 * The TCCTechnicalException Class
 * 
 * @author Paulo Pacheco
 *
 */
public class TCCTechnicalException extends Exception {

	private static final long serialVersionUID = -4544641597223356338L;

	public TCCTechnicalException() {}
	
	public TCCTechnicalException(String message) {
		super(message);
	}
	
	public TCCTechnicalException(Throwable cause) {
		super(cause);
	}
	
	public TCCTechnicalException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TCCTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
