package mx.escuela.datos.dao.tables.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_calificaciones", schema = Constantes.SCHEMA_ESCUELA)
public class Calificaciones implements Serializable {

	private static final long serialVersionUID = 9017137662120864996L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_calificaciones", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_t_materias", nullable = false, foreignKey = @ForeignKey(name = "t_calificaciones_id_t_materias_fkey"))
	private Materias materia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_t_usuarios", nullable = false, foreignKey = @ForeignKey(name = "t_calificaciones_id_t_usuarios_fkey"))
	private Alumnos alumno;
	
	@Column(name = "calificacion", nullable = true)
	private Double calificacion; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = true)
	private Date fechaRegistro;
	
	@Column(name = "activo", nullable = true)
	private Boolean activo;

}
