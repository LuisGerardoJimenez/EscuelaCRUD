package mx.escuela.negocio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.icu.text.DecimalFormat;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.dto.CalificacionDTO;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.comun.util.Formateador;
import mx.escuela.datos.dao.tables.impl.Alumnos;
import mx.escuela.datos.dao.tables.impl.Calificaciones;
import mx.escuela.datos.dao.tables.impl.Materias;
import mx.escuela.exception.NegocioException;
import mx.escuela.negocio.ICalificacionNegocio;
import mx.escuela.servicios.ICalificacionesService;

@Slf4j
@Component
public class CalificacionNegocioImpl implements ICalificacionNegocio{
	
	@Autowired
	private ICalificacionesService calificacionesService;

	@Override
	public CalificacionDTO consultaCalificacionesPorIdAlumno(Integer idAlumno) {
		try {
			List<Calificaciones> calificacionesList = this.calificacionesService.consultaCalificacionesPorIdAlumno(idAlumno, Boolean.TRUE);
			if(calificacionesList != null && !calificacionesList.isEmpty()) {
				CalificacionDTO calificacionDTO = new CalificacionDTO();
				List<CalificacionDTO> calificacionDTOList = new ArrayList<>();
				Double promedio = 0.0;
				Double sumaCal = 0.0;
				for(Calificaciones calificacion :calificacionesList){
					CalificacionDTO dto = new CalificacionDTO();
					dto.setIdCalificacion(calificacion.getId());
					dto.setMateria(calificacion.getMateria().getNombre());
					dto.setNombre(calificacion.getAlumno().getNombre());
					dto.setApellido(calificacion.getAlumno().getApellidoPaterno());
					dto.setCalificacion(calificacion.getCalificacion());
					dto.setFechaRegistro(Formateador.formatoFecha(calificacion.getFechaRegistro()));
					sumaCal+=calificacion.getCalificacion();
					calificacionDTOList.add(dto);
				}
				promedio = sumaCal/calificacionesList.size();
				DecimalFormat df = new DecimalFormat("0.00");
				calificacionDTO.setPromedio(Double.valueOf(df.format(promedio)));
				calificacionDTO.setCalificacionDTOList(calificacionDTOList);
				calificacionDTO.setCode(ConstantesGlobales.ID_EXITO);
				return calificacionDTO;
			} else {
				return new CalificacionDTO("No se encontraron resultados",
						null,
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_NO_SE_ENCONTRARON_RESULTADOS);
			}
		} catch (NegocioException e) {
			return new CalificacionDTO(e.getUserMessage(),
					e.getInternalMessage(),
					CalificacionNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new CalificacionDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al consultar Calificaciones",
					CalificacionNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}

	@Override
	public CalificacionDTO altaCalificacion(CalificacionDTO calificacionDTO) {
		try {
			if (calificacionDTO == null) {
				return new CalificacionDTO("El DTO esta vacio.", "El DTO esta vacio.",
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_ERROR_PARAMETROS);
			}
			String mensajeValidacion = this.validarCampos(calificacionDTO, Boolean.FALSE);
			if(mensajeValidacion == null || mensajeValidacion.isEmpty()) {
				Calificaciones calificacion = new Calificaciones();
				calificacion.setMateria(new Materias(calificacionDTO.getIdMateria()));
				calificacion.setAlumno(new Alumnos(calificacionDTO.getIdAlumno()));
				calificacion.setCalificacion(calificacionDTO.getCalificacion());
				calificacion.setFechaRegistro(new Date());
				calificacion = this.calificacionesService.altaCalificacion(calificacion);
				calificacionDTO.setIdCalificacion(calificacion.getId());
				calificacionDTO.setSuccess("OK");
				calificacionDTO.setMsg("Calificaci\u00F3n registrada");
				calificacionDTO.setCode(ConstantesGlobales.ID_EXITO);
				return calificacionDTO;
			} else {
				return new CalificacionDTO(mensajeValidacion, 
						null, 
						CalificacionNegocioImpl.class.toString(),
						ConstantesGlobales.ID_ERROR_DE_NEGOCIO);
			}
		} catch (NegocioException e) {
			return new CalificacionDTO(e.getUserMessage(),
					e.getInternalMessage(),
					CalificacionNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new CalificacionDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al registrar Calificaci\u00F3n",
					CalificacionNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}

	@Override
	public CalificacionDTO editaCalificacion(CalificacionDTO calificacionDTO) {
		try {
			if (calificacionDTO == null) {
				return new CalificacionDTO("El DTO esta vacio.", "El DTO esta vacio.",
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_ERROR_PARAMETROS);
			}
			String mensajeValidacion = this.validarCampos(calificacionDTO, Boolean.TRUE);
			if(mensajeValidacion == null || mensajeValidacion.isEmpty()) {
				Calificaciones calificacionDB = this.calificacionesService.
												consultaCalificacionPorId(calificacionDTO.getIdCalificacion());
				if(calificacionDB != null) {
					calificacionDB.setMateria(new Materias(calificacionDTO.getIdMateria()));
					calificacionDB.setAlumno(new Alumnos(calificacionDTO.getIdAlumno()));
					calificacionDB.setCalificacion(calificacionDTO.getCalificacion());
					calificacionDB.setFechaRegistro(new Date());
					calificacionDB = this.calificacionesService.altaCalificacion(calificacionDB);
					calificacionDTO.setSuccess("OK");
					calificacionDTO.setMsg("Calificaci\u00F3n actualizada");
					calificacionDTO.setCode(ConstantesGlobales.ID_EXITO);
					return calificacionDTO;
				} else {
					return new CalificacionDTO("Ocurri\u00F3 un error al consultar informaci\u00F3n adicional actualizaci\u00F3n",
							"La calificacion es Nula",
							CalificacionNegocioImpl.class.toString(), 
							ConstantesGlobales.ID_NO_SE_ENCONTRARON_RESULTADOS);
				}
			} else {
				return new CalificacionDTO(mensajeValidacion, 
						null, 
						CalificacionNegocioImpl.class.toString(),
						ConstantesGlobales.ID_ERROR_DE_NEGOCIO);
			}
		} catch (NegocioException e) {
			return new CalificacionDTO(e.getUserMessage(),
					e.getInternalMessage(),
					CalificacionNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new CalificacionDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al actualizar Calificaci\u00F3n",
					CalificacionNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}

	@Override
	public CalificacionDTO eliminaCalificacion(CalificacionDTO calificacionDTO) {
		try {
			if (calificacionDTO == null) {
				return new CalificacionDTO("El DTO esta vacio.", "El DTO esta vacio.",
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_ERROR_PARAMETROS);
			}
			
			if (calificacionDTO.getIdCalificacion() == null) {
				return new CalificacionDTO("El IdCalificacion es obligatorio", "El IdCalificacion es obligatorio",
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_ERROR_DE_NEGOCIO);
			}
			
			Calificaciones calificacionDB = this.calificacionesService.
					consultaCalificacionPorId(calificacionDTO.getIdCalificacion());
			if(calificacionDB != null) {
				this.calificacionesService.eliminaCalificacion(calificacionDB);
				calificacionDTO.setSuccess("OK");
				calificacionDTO.setMsg("Calificaci\u00F3n eliminada");
				calificacionDTO.setCode(ConstantesGlobales.ID_EXITO);
				return calificacionDTO;
			} else {
				return new CalificacionDTO("Ocurri\u00F3 un error al consultar informaci\u00F3n adicional eliminaci\u00F3n",
						"La calificacion es Nula",
						CalificacionNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_NO_SE_ENCONTRARON_RESULTADOS);
			}
		} catch (NegocioException e) {
			return new CalificacionDTO(e.getUserMessage(),
					e.getInternalMessage(),
					CalificacionNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new CalificacionDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al eliminar Calificaci\u00F3n",
					CalificacionNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}
	
	private String validarCampos(CalificacionDTO calificacionDTO, Boolean edicion) {
		String mensaje = "";
		//Valida campos obligatorios
		if(edicion) {
			if(calificacionDTO.getIdCalificacion() == null) {
				mensaje = "El idCalificacion es obligatorio";
			}
		}
		if(calificacionDTO.getIdMateria() == null || calificacionDTO.getIdMateria().equals(ConstantesGlobales.NUMERO_CERO)) {
			mensaje = "El idMateria es obligatorio";
		}
		if(calificacionDTO.getIdAlumno() == null || calificacionDTO.getIdAlumno().equals(ConstantesGlobales.NUMERO_CERO)) {
			mensaje = "El idAlumno es obligatorio";
		}
		if(calificacionDTO.getCalificacion() == null) {
			mensaje = "La calificacion es obligatorio";
		}
		return mensaje;
	}

}
