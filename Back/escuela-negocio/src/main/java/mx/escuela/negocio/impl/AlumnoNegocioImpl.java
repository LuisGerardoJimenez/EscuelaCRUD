package mx.escuela.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.dto.AlumnoDTO;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.impl.Alumnos;
import mx.escuela.exception.NegocioException;
import mx.escuela.negocio.IAlumnoNegocio;
import mx.escuela.servicios.IAlumnosService;

@Slf4j
@Component
public class AlumnoNegocioImpl implements IAlumnoNegocio{
	
	@Autowired
	private IAlumnosService alumnosService;

	@Override
	public AlumnoDTO consultaAlumnos() {
		try {
			List<Alumnos> alumnosList = this.alumnosService.consultaAlumnos(Boolean.TRUE);
			if(alumnosList != null && !alumnosList.isEmpty()) {
				AlumnoDTO alumnoDTO = new AlumnoDTO();
				List<AlumnoDTO> alumnosDTOList = new ArrayList<>();
				for(Alumnos alumno :alumnosList){
					AlumnoDTO dto = new AlumnoDTO();
					dto.setIdAlumno(alumno.getId());
					StringBuilder nombreCompleto = new StringBuilder();
					if(alumno.getNombre() != null) {
						nombreCompleto.append(alumno.getNombre());
					}
					if(alumno.getApellidoPaterno() != null) {
						nombreCompleto.append(" ").append(alumno.getApellidoPaterno());
					}
					if(alumno.getApellidoMaterno() != null) {
						nombreCompleto.append(" ").append(alumno.getApellidoMaterno());
					}
					dto.setNombreCompleto(nombreCompleto.toString());
					alumnosDTOList.add(dto);
				}
				alumnoDTO.setAlumnosDTOList(alumnosDTOList);
				alumnoDTO.setCode(ConstantesGlobales.ID_EXITO);
				return alumnoDTO;
			} else {
				return new AlumnoDTO("No se encontraron resultados",
						null,
						AlumnoNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_NO_SE_ENCONTRARON_RESULTADOS);
			}
		} catch (NegocioException e) {
			return new AlumnoDTO(e.getUserMessage(),
					e.getInternalMessage(),
					AlumnoNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new AlumnoDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al consultar Alumnos",
					AlumnoNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}

}
