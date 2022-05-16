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
public class LaboratorioDTO extends ExceptionResponseDTO implements Serializable{

	private static final long serialVersionUID = -6924125145151319891L;
	
	private Integer idLaboratorio;
	private Integer idInforme;
	private Integer idCasoLaboratorio;
	private String folioInforme;
	private String casoLaboratorio;
	private String propietario;
	private String nombreUpp;
	private String numeroUpp;
	private Long idEntidad;
	private Long idMunicipio;
	private Long idLocalidad;
	private String latitud;
	private String longitud;
	private Integer idMotivoPrueba;
	private Long totalPoblacion;
	private Long totalEnfermos;
	private Long totalMuertos;
	private Integer idTipoExplotacion;
	private Integer idTipoVigilancia;
	//Bandera eliminado
	private Boolean eliminado;
	//Consulta
	private String entidad;
	private String municipio;
	private String localidad;
	private String motivoPrueba;
	private String tipoExplotacion;
	private String tipoVigilancia;
	private List<LaboratorioDTO> laboratoriosList;
	
	public LaboratorioDTO(String userMessage, String internalMessage, String moreInfo, String code) {
		super(userMessage, internalMessage, moreInfo, code);
	}

	public LaboratorioDTO() {
		
	}

}
