package mx.escuela.comun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formateador {

	/**
	 * convierte Date a String dd/MM/yyyy
	 * @param fecha
	 * @return
	 */
	public static String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComoCadena = sdf.format(fecha);
		return fechaComoCadena;
	}

}
