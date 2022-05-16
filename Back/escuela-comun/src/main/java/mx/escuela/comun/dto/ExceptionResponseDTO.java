package mx.escuela.comun.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
* @author Luis Gerardo Jiménez
* @since 2022/05/14
* @version 1.0
*/

@Getter
@Setter
public class ExceptionResponseDTO implements Serializable{

	private static final long serialVersionUID = 4300211630623078784L;
	
	private Date timestamp;
	private String userMessage;
	private String internalMessage;
	private String moreInfo;
	private String code;
	
	public ExceptionResponseDTO() {
		
	}
	
	public ExceptionResponseDTO( String userMessage, String internalMessage, String moreInfo,
			String code) {
		super();
		this.userMessage = userMessage;
		this.internalMessage = internalMessage;
		this.moreInfo = moreInfo;
		this.code = code;
		this.timestamp=new Date();
	}

}
