package mx.escuela.comun.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
* @author Luis Gerardo Jiménez
* @since 2022/05/14
* @version 1.0
*/

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO extends ExceptionResponseDTO implements Serializable{

	private static final long serialVersionUID = -8188947205810550491L;
	
	private String usuario;
	private String rol;
	private Integer idLaboratorio;
	private String laboratorio;
	
	public UsuarioDTO(String userMessage, String internalMessage, String moreInfo, String code) {
		super(userMessage, internalMessage, moreInfo, code);
	}

	public UsuarioDTO() {
		
	}

}
