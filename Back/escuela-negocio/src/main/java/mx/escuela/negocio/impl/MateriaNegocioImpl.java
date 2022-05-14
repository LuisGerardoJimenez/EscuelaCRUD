package mx.escuela.negocio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.dto.MateriaDTO;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.impl.Materias;
import mx.escuela.exception.NegocioException;
import mx.escuela.negocio.IMateriaNegocio;
import mx.escuela.servicios.IMateriasService;

@Slf4j
@Component
public class MateriaNegocioImpl implements IMateriaNegocio{
	
	@Autowired
	private IMateriasService materiasService;

	@Override
	public MateriaDTO consultaMaterias() {
		try {
			List<Materias> materiasList = this.materiasService.consultaMaterias(Boolean.TRUE);
			if(materiasList != null && !materiasList.isEmpty()) {
				MateriaDTO materiaDTO = new MateriaDTO();
				List<MateriaDTO> materiasDTOList = new ArrayList<>();
				for(Materias materia :materiasList){
					MateriaDTO dto = new MateriaDTO();
					dto.setIdMateria(materia.getId());
					dto.setMateria(materia.getNombre());
					materiasDTOList.add(dto);
				}
				materiaDTO.setMateriaDTOList(materiasDTOList);
				materiaDTO.setCode(ConstantesGlobales.ID_EXITO);
				return materiaDTO;
			} else {
				return new MateriaDTO("No se encontraron resultados",
						null,
						MateriaNegocioImpl.class.toString(), 
						ConstantesGlobales.ID_NO_SE_ENCONTRARON_RESULTADOS);
			}
		} catch (NegocioException e) {
			return new MateriaDTO(e.getUserMessage(),
					e.getInternalMessage(),
					MateriaNegocioImpl.class.toString(), 
					e.getCode());
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error general ", e);
			return new MateriaDTO("Ocurri\u00F3 un error general.",
					"Ocurri\u00F3 un error general al consultar Materias",
					MateriaNegocioImpl.class.toString(), 
					ConstantesGlobales.ID_ERROR_GENERAL_CAPA_NEGOCIO);
		}
	}

}
