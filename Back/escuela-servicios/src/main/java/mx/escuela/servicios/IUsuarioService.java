package mx.escuela.servicios;

import mx.escuela.datos.dao.tables.impl.Usuarios;

public interface IUsuarioService {

	Usuarios getUser();

	Usuarios findByUsername(String username);

}
