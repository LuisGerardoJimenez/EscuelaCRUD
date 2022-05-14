package mx.escuela.negocio.utilerias;

import java.util.regex.Pattern;

public class Validador {

	public static boolean isDDMMYYYY(String textFecha) {
		if (textFecha != null) {
			if (Pattern.matches("((0[1-9]|[12]\\d|3[01])\\/(0[1-9]|1[0-2])\\/[12]\\d{3})", textFecha))
				return true;
		} 
		return false;
	}

}
