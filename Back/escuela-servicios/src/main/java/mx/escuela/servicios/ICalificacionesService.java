package mx.escuela.servicios;

import java.util.List;

import mx.escuela.datos.dao.tables.impl.Calificaciones;
import mx.escuela.exception.NegocioException;

public interface ICalificacionesService {
	
	Calificaciones consultaCalificacionPorId(Integer idCalificacion) throws NegocioException;
	
	List<Calificaciones> consultaCalificacionesPorIdAlumno(Integer idAlumno, Boolean activo) throws NegocioException;
	
	Calificaciones altaCalificacion(Calificaciones calificacion) throws NegocioException;
	
	Calificaciones editaCalificacion(Calificaciones calificacion) throws NegocioException;
	
	Boolean eliminaCalificacion(Calificaciones calificacion) throws NegocioException;

}
