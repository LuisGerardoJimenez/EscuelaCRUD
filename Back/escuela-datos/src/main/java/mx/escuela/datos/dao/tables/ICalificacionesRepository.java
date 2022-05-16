package mx.escuela.datos.dao.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.escuela.datos.dao.tables.impl.Calificaciones;

@Repository
public interface ICalificacionesRepository extends JpaRepository<Calificaciones, Integer> {
	
	Calificaciones findAllById(Integer idCalificacion);
	
	List<Calificaciones> findAllByAlumnoIdAndActivo(Integer idAlumno, Boolean activo);

}
