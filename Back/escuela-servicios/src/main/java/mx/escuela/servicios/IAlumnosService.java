package mx.escuela.servicios;

import java.util.List;

import mx.escuela.datos.dao.tables.impl.Alumnos;
import mx.escuela.exception.NegocioException;

public interface IAlumnosService {
	
	List<Alumnos> consultaAlumnos(Boolean activo) throws NegocioException;
	
	Alumnos altaAlumno(Alumnos alumno) throws NegocioException;
	
	Alumnos editaAlumno(Alumnos alumno) throws NegocioException;
	
	Boolean eliminaAlumno(Alumnos alumno) throws NegocioException;

}
