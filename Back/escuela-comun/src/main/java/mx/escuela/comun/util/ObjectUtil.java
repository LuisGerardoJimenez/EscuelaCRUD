package mx.escuela.comun.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtil {

	public static String getRutaReportesJasper() {

		String ruta = "";

		final File f = new File(ObjectUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		try {
			
			// Encuentro documento
			ruta = new ClassPathResource(ConstantesGlobales.SPLIT_PARA_LA_RUTA_JASPER).getFile().getAbsolutePath() + "//";
			
			 File archivo = new File(ruta + ConstantesGlobales.REPORTE_BASE);
			//ruta = archivo.path
			log.debug("Ruta: "+archivo.getAbsolutePath());
			
			if (!archivo.exists()) {
				ruta = f.getCanonicalPath();

				String[] arrOfStr2 = ruta.split(ConstantesGlobales.NOMBRE_DEL_MODULO_COMUN_WAR);
				ruta = arrOfStr2[0] + ConstantesGlobales.SPLIT_PARA_LA_RUTA_JASPER_WAR;
			}
			
			log.debug(ruta);
			
		} catch (IOException e) {
			log.error("Ocurrio un error de entrada y salida",e);
			ruta = "";
		}
		return ruta;
	}

	

}
