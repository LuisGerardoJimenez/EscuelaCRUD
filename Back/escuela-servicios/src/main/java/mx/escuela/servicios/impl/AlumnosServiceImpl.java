package mx.escuela.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.IAlumnosRepository;
import mx.escuela.datos.dao.tables.impl.Alumnos;
import mx.escuela.exception.NegocioException;
import mx.escuela.servicios.IAlumnosService;

@Slf4j
@Component
public class AlumnosServiceImpl implements IAlumnosService {
	
	@Autowired
	private IAlumnosRepository alumnosRepository;
	
	@Override
	public List<Alumnos> consultaAlumnos(Boolean activo) throws NegocioException {
		try {
			return this.alumnosRepository.findAllByActivo(activo);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al consultar Alumno :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al consultar alumno", 
					e.getMessage(), 
					AlumnosServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Alumnos altaAlumno(Alumnos alumno) throws NegocioException {
		try {
			alumno.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.alumnosRepository.save(alumno);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al registrar Alumno :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al insertar alumno", 
					e.getMessage(), 
					AlumnosServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}
	
	@Override
	public Alumnos editaAlumno(Alumnos alumno) throws NegocioException {
		try {
			alumno.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.alumnosRepository.save(alumno);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al editar Alumno :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al editar alumno", 
					e.getMessage(), 
					AlumnosServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}
	
	@Override
	public Boolean eliminaAlumno(Alumnos alumno) throws NegocioException {
		try {
			alumno.setActivo(ConstantesGlobales.BOOLEAN_INACTIVO);
			this.alumnosRepository.save(alumno);
			return Boolean.TRUE;
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al eliminar Alumno :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al eliminar alumno", 
					e.getMessage(), 
					AlumnosServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

}
