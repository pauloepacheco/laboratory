package br.com.ulbra.tcc.common.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The ErrorMessage Class
 * 
 * @author Paulo Pacheco
 *
 */
@XmlRootElement
public class ErrorMessage {
	
	public ErrorMessage() {}
	
	/** message describing the error*/
	@XmlElement(name = "message")
	String message;
	
	/** extra information that might useful for developers */
	@XmlElement(name = "developerMessage")
	String developerMessage;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}	
}
