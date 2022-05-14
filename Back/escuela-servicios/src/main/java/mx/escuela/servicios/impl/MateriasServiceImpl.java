package mx.escuela.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.IMateriasRepository;
import mx.escuela.datos.dao.tables.impl.Materias;
import mx.escuela.exception.NegocioException;
import mx.escuela.servicios.IMateriasService;

@Slf4j
@Component
public class MateriasServiceImpl implements IMateriasService {
	
	@Autowired
	private IMateriasRepository materiasRepository;
	
	@Override
	public List<Materias> consultaMaterias(Boolean activo) throws NegocioException {
		try {
			return this.materiasRepository.findAllByActivo(activo);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al consultar Materia :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al consultar Materia", 
					e.getMessage(), 
					MateriasServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Materias altaMateria(Materias materia) throws NegocioException {
		try {
			materia.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.materiasRepository.save(materia);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al registrar Materia :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al insertar Materia", 
					e.getMessage(), 
					MateriasServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Materias editaMateria(Materias materia) throws NegocioException {
		try {
			materia.setActivo(ConstantesGlobales.BOOLEAN_ACTIVO);
			return this.materiasRepository.save(materia);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al editar Materia :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al editar Materia", 
					e.getMessage(), 
					MateriasServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

	@Override
	public Boolean eliminaMateria(Materias materia) throws NegocioException {
		try {
			materia.setActivo(ConstantesGlobales.BOOLEAN_INACTIVO);
			this.materiasRepository.save(materia);
			return Boolean.TRUE;
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al eliminar Materia :: ", e);
			throw new NegocioException("Ocurri\u00F3 un error al eliminar Materia", 
					e.getMessage(), 
					MateriasServiceImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_SERVICIOS);
		}
	}

}
