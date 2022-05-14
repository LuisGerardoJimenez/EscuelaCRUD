package mx.escuela.datos.dao.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.escuela.datos.dao.tables.impl.Materias;

@Repository
public interface IMateriasRepository extends JpaRepository<Materias, Integer> {
	
	Materias findAllById(Integer idMateria);
	
	List<Materias> findAllByActivo(Boolean activo);

}
