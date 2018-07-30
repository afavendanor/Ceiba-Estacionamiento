package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Date;

public class DFecha {

	private static DFecha gestionFecha;
	private static final int SEGUNDOS_HORA = 3600;

	private DFecha() {

	}

	public static DFecha getInstance() {
		if (gestionFecha == null) {
			gestionFecha = new DFecha();
		}
		return gestionFecha;
	}
	
	public int retornarHorasParqueo(Date fechaIngreso, Date fechaSalida) {
		int diferencia = (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / 1000);
		return (diferencia / SEGUNDOS_HORA != 0 ? diferencia / SEGUNDOS_HORA : 1);
		
	}

}
