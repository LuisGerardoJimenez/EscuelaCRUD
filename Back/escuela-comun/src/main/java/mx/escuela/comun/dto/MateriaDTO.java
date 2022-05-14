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
public class MateriaDTO extends ExceptionResponseDTO implements Serializable{

	private static final long serialVersionUID = 3484374957898372311L;
	
	private Integer idMateria;
	private String materia;
	private List<MateriaDTO> materiaDTOList;
	
	public MateriaDTO(String userMessage, String internalMessage, String moreInfo, String code) {
		super(userMessage, internalMessage, moreInfo, code);
	}

	public MateriaDTO() {
		
	}

}
