package mx.escuela.datos.dao.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.escuela.datos.dao.tables.impl.Alumnos;

@Repository
public interface IAlumnosRepository extends JpaRepository<Alumnos, Integer> {
	
	Alumnos findAllById(Integer idAlumno);
	
	List<Alumnos> findAllByActivo(Boolean activo);

}
