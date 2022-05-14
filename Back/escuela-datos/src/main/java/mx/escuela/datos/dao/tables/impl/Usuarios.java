package mx.escuela.datos.dao.tables.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.escuela.datos.utilerias.Constantes;

/**
* @author Luis Gerardo Jiménez
* @since 2022/05/14
* @version 1.0
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", schema = Constantes.SCHEMA_ESCUELA)
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 7425931306800883878L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private Integer id;
	
	@Column(name = "usuario", nullable = false)
	private String usuario;
	
	@Column(name = "contrasenia", nullable = false)
	private String contrasenia;
	
	@Column(name = "rol", nullable = false)
	private String rol;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = true)
	private Date fechaRegistro;
	
	@Column(name = "activo", nullable = true)
	private Boolean activo;

}
