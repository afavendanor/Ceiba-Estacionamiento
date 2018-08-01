package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Date;

public final class Calendario {

	private static Calendario gestionFecha;
	private static final int SEGUNDOS_HORA = 3600;

	private Calendario() {

	}

	public static Calendario getInstance() {
		if (gestionFecha == null) {
			gestionFecha = new Calendario();
		}
		return gestionFecha;
	}
	
	public int retornarHorasParqueo(Date fechaIngreso, Date fechaSalida) {
		int diferencia = (int) ((fechaSalida.getTime() - fechaIngreso.getTime()) / 1000);
		return (diferencia / SEGUNDOS_HORA != 0 ? diferencia / SEGUNDOS_HORA : 1);
		
	}

}
