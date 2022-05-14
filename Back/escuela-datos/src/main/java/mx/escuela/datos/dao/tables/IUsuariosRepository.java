package mx.escuela.datos.dao.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.escuela.datos.dao.tables.impl.Usuarios;
import mx.escuela.datos.utilerias.Constantes;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
	
	Usuarios findAllById(Integer idUsuario);
	
	@Query(value = "SELECT * FROM " + Constantes.SCHEMA_ESCUELA + ".usuarios u WHERE u.usuario = :user " +
			"AND u.activo != :estatus", nativeQuery = true)
	Usuarios findByUserAndDistinctStatus(@Param("user") String usuario, @Param("estatus") Boolean estatus);

}
