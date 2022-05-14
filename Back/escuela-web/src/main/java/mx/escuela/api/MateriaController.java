package mx.escuela.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.escuela.comun.dto.MateriaDTO;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.negocio.IMateriaNegocio;

@RestController
@RequestMapping("/materias")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class MateriaController {
	
	@Autowired
	private IMateriaNegocio materiaNegocio;
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@GetMapping(path = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findInformesLaboratoriosMuestraByInforme() throws Exception {
		MateriaDTO response = this.materiaNegocio.consultaMaterias();
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
