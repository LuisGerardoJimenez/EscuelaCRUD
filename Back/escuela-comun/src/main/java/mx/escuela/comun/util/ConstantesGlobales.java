package mx.escuela.comun.util;

public class ConstantesGlobales {

	/* Codigo errores definido */
	public static final String ID_EXITO = "0";
	public static final String ID_ERROR_GENERAL_CAPA_DATOS = "-1";
	public static final String ID_ERROR_GENERAL_CAPA_SERVICIOS = "-2";
	public static final String ID_ERROR_GENERAL_CAPA_NEGOCIO = "-3";
	public static final String ID_ERROR_GENERAL_CAPA_WEB = "-4";
	public static final String ID_ERROR_PARAMETROS = "1";
	public static final String ID_ERROR_DE_NEGOCIO = "2";
	public static final String ID_NO_SE_ENCONTRARON_RESULTADOS = "3";
	public static final String ID_NO_SE_PUDO_INSERTAR_ACTUALIZAR = "4";
	public static final String ID_DUPLICADO = "5";
	
	/* ROLES*/
	public static final String ROL_ALL = "all";
	public static final String ROL_ADMIN = "Admin";

	/* ESTATUS de usuarios */
	public static final Boolean BOOLEAN_INACTIVO = false;
	public static final Boolean BOOLEAN_ACTIVO = true;


	// ES PARA GENERAR LA RUTA PARA OBTENER LOS REPORTES JASPER
	public static final String NOMBRE_DEL_MODULO_COMUN = "escuela-comun";
	public static final String SPLIT_PARA_LA_RUTA_JASPER = "reportes//";

	
	public static final String NOMBRE_DEL_MODULO_COMUN_WAR = "applications";
	public static final String SPLIT_PARA_LA_RUTA_JASPER_WAR = "//applications//reportes//escuela//";

	// NOMBRE DE LOS REPORTES
	public static final String REPORTE_BASE = "reporte_base.jasper";
	
	//NUMEROS
	public static final Integer NUMERO_CERO = 0;
	public static final Integer NUMERO_UNO = 1;


}
