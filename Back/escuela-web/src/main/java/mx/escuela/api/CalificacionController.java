package mx.escuela.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.escuela.comun.dto.CalificacionDTO;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.negocio.ICalificacionNegocio;

@RestController
@RequestMapping("/calificacion")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CalificacionController {
	
	@Autowired
	private ICalificacionNegocio calificacionNegocio;
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@GetMapping(path = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findCalificacionById(
			@RequestParam (value = "calificacion", required = true) Integer idCalificacion) throws Exception {
		CalificacionDTO response = this.calificacionNegocio.consultaCalificacionPorId(idCalificacion);
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@GetMapping(path = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findCalificacionesByIdAlumno(
			@RequestParam (value = "alumno", required = true) Integer idAlumno) throws Exception {
		CalificacionDTO response = this.calificacionNegocio.consultaCalificacionesPorIdAlumno(idAlumno);
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@PostMapping(path = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> altaCalificacion(@RequestBody CalificacionDTO calificacionDTO) throws Exception {
		CalificacionDTO response = this.calificacionNegocio.altaCalificacion(calificacionDTO);
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@PutMapping(path = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizaCalificacion(@RequestBody CalificacionDTO calificacionDTO) throws Exception {
		CalificacionDTO response = this.calificacionNegocio.editaCalificacion(calificacionDTO);
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("@authorityServiceImpl.hasAccess('"+ConstantesGlobales.ROL_ADMIN+"')")
	@DeleteMapping(path = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminaInformeLaboratorio(
			@RequestParam (value = "calificacion", required = true) Integer idCalificacion) throws Exception {
		CalificacionDTO response = this.calificacionNegocio.eliminaCalificacion(idCalificacion);
		if (response != null && response.getCode() != null 
				&& response.getCode().equals(ConstantesGlobales.ID_EXITO)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
