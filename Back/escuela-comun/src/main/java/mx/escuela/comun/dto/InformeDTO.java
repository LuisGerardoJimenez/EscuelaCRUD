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
public class InformeDTO extends ExceptionResponseDTO implements Serializable {

	private static final long serialVersionUID = -8923501492692097322L;
	
	private Integer idInforme;
	private String fechaApertura;
	private String fechaUltimaEdicion;
	
	private String folioInforme;
	private String fechaEnvio;
	
	//Bandera eliminado
	private Boolean eliminado;
	
	public InformeDTO(String userMessage, String internalMessage, String moreInfo, String code) {
		super(userMessage, internalMessage, moreInfo, code);
	}

	public InformeDTO() {
		
	}

}
