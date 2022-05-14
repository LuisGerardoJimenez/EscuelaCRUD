package mx.escuela.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.ICalificacionesRepository;
import mx.escuela.datos.dao.tables.impl.Calificaciones;
import mx.escuela.exception.NegocioException;
import mx.escuela.servicios.ICalificacionesService;

@Slf4j
@Component
public class CalificacionesServiceImpl implements ICalificacionesService {
	
	@Autowired
	private ICalificacionesRepository calificacionesRepository;
	
	@Override
	public Calificaciones consultaCalificacionPorId(Integer idCalificacion) throws NegocioException {
		try {
			return this.calificacionesRepository.findAllById(idCalificacion);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al consultar Calificaci\u00F3n por ID:: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al consultar Calificaci\u00F3n por ID", 
					e.getMessage(), 
					CalificacionesServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}
	
	@Override
	public List<Calificaciones> consultaCalificacionesPorIdAlumno(Integer idAlumno, Boolean activo) throws NegocioException {
		try {
			return this.calificacionesRepository.findAllByAlumnoIdAndActivo(idAlumno, activo);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al consultar Calificaci\u00F3n por Id Alumno:: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al consultar Calificaci\u00F3n por Id Alumno", 
					e.getMessage(), 
					CalificacionesServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Calificaciones altaCalificacion(Calificaciones calificacion) throws NegocioException {
		try {
			calificacion.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.calificacionesRepository.save(calificacion);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al registrar Calificaci\u00F3n :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al insertar Calificaci\u00F3n", 
					e.getMessage(), 
					CalificacionesServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Calificaciones editaCalificacion(Calificaciones calificacion) throws NegocioException {
		try {
			calificacion.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.calificacionesRepository.save(calificacion);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al editar Calificaci\u00F3n :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al editar Calificaci\u00F3n", 
					e.getMessage(), 
					CalificacionesServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Boolean eliminaCalificacion(Calificaciones calificacion) throws NegocioException {
		try {
			this.calificacionesRepository.delete(calificacion);
			return Boolean.TRUE;
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al editar Calificaci\u00F3n :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al editar Calificaci\u00F3n", 
					e.getMessage(), 
					CalificacionesServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

}
