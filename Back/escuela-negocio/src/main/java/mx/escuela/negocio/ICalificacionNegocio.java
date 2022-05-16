package mx.escuela.negocio;

import mx.escuela.comun.dto.CalificacionDTO;

public interface ICalificacionNegocio {
	
	CalificacionDTO consultaCalificacionPorId(Integer idCalificacion);
	
	CalificacionDTO consultaCalificacionesPorIdAlumno(Integer idAlumno);
	
	CalificacionDTO altaCalificacion(CalificacionDTO calificacionDTO);
	
	CalificacionDTO editaCalificacion(CalificacionDTO calificacionDTO);
	
	CalificacionDTO eliminaCalificacion(Integer idCalificacionDTO);

}
