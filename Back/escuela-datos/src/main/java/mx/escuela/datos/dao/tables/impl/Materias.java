package mx.escuela.datos.dao.tables.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "t_materias", schema = Constantes.SCHEMA_ESCUELA)
public class Materias implements Serializable {

	private static final long serialVersionUID = 7491908959205873220L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_materias", nullable = false)
	private Integer id;
	
	@Column(name = "nombre", nullable = true)
	private String nombre;
	
	@Column(name = "activo", nullable = true)
	private Boolean activo;
	
	public Materias(Integer id) {
		this.id=id;
	}

}
