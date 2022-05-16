package mx.escuela.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
* @author Luis Gerardo Jiménez
* @since 2022/05/14
* @version 1.0
*/

@Getter @Setter
public class NegocioException extends Exception {

	private static final long serialVersionUID = 6446664287250234273L;
	
	private String userMessage;
	private String internalMessage;
	private String moreInfo;
	private String code;
	private Date timestamp;
	
	public NegocioException() {
		
	}
	
	public NegocioException( String userMessage, String internalMessage, String moreInfo,
			String code) {
		this.userMessage = userMessage;
		this.internalMessage = internalMessage;
		this.moreInfo = moreInfo;
		this.code = code;
		this.timestamp = new Date();
	}

}
