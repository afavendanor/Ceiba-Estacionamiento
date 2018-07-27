package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;

public final class DVehiculo {
	
	private static DVehiculo vehiculo;

	private DVehiculo() {

	}

	public static DVehiculo getInstance() {
		if (vehiculo == null) {
			vehiculo = new DVehiculo();
		}
		return vehiculo;
	}
	
	public void validarHabilitacion(String placa, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		System.out.println("-----------> " + cal.get(Calendar.DAY_OF_WEEK));
		if (validarPlacaConA(placa)
				&& (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)) {
			throw new VehiculoNoAutorizadoException("El vehiculo no esta autorizado para ingresar");
		}
	}
	
	public boolean validarPlacaConA(String placa) {
		return "A".equalsIgnoreCase(placa.substring(0, 1));
	}

}
