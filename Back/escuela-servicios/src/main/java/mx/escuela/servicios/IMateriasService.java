package mx.escuela.servicios;

import java.util.List;

import mx.escuela.datos.dao.tables.impl.Materias;
import mx.escuela.exception.NegocioException;

public interface IMateriasService {
	
	List<Materias> consultaMaterias(Boolean activo) throws NegocioException;
	
	Materias altaMateria(Materias materia) throws NegocioException;
	
	Materias editaMateria(Materias materia) throws NegocioException;
	
	Boolean eliminaMateria(Materias materia) throws NegocioException;

}
