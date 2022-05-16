package mx.escuela.comun.dto;

import java.io.Serializable;
import java.util.List;

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
public class CalificacionDTO extends ExceptionResponseDTO implements Serializable{

	private static final long serialVersionUID = 3484374957898372311L;
	
	private Integer idAlumno;
	private String nombre;
	private String apellido;
	private Integer idMateria;
	private String materia;
	private Integer idCalificacion;
	private Double calificacion;
	private String fechaRegistro;
	private Double promedio;
	private String success;
	private String msg;
	private List<CalificacionDTO> calificacionDTOList; 
	
	public CalificacionDTO(String userMessage, String internalMessage, String moreInfo, String code) {
		super(userMessage, internalMessage, moreInfo, code);
	}

	public CalificacionDTO() {
		
	}

}
