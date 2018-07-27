package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Calendar;

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
	
	public boolean validarHabilitacion(String placa, Calendar fecha) {
		if (validarPlacaConA(placa)
				&& !(fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fecha.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
			throw new VehiculoNoAutorizadoException("El vehiculo no esta autorizado para ingresar");
		}
		return true;
	}
	
	public boolean validarPlacaConA(String placa) {
		return "A".equalsIgnoreCase(placa.substring(0, 1));
	}

}
