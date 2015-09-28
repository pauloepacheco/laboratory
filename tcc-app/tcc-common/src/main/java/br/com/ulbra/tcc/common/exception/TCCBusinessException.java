package br.com.ulbra.tcc.common.exception;

/**
 * The TCCBusinessException Class
 * 
 * @author Paulo Pacheco
 *
 */
public class TCCBusinessException extends Exception {

	private static final long serialVersionUID = 7044352595658639935L;

	public TCCBusinessException() {}
	
	public TCCBusinessException(String message) {
		super(message);
	}
	
	public TCCBusinessException(Throwable cause) {
		super(cause);
	}
	
	public TCCBusinessException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TCCBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
