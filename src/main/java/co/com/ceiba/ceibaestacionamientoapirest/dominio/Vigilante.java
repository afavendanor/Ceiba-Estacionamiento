package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public final class Vigilante {

	private static final int VEHICULO_REGISTRADO = 1;

	private static Vigilante vigilante;

	private Vigilante() {

	}

	public static Vigilante getInstance() {
		if (vigilante == null) {
			vigilante = new Vigilante();
		}
		return vigilante;
	}

	public void validarDisponibilidad(TipoVehiculo tipoVehiculo, int vehiculosParqueados) {
		if (!((TipoVehiculo.CARRO.equals(tipoVehiculo) && vehiculosParqueados < Constantes.NUMERO_CARROS_PERMITIDOS)
				|| (TipoVehiculo.MOTO.equals(tipoVehiculo)
						&& vehiculosParqueados < Constantes.NUMERO_MOTOS_PERMITIDAS))) {
			throw new VehiculoNoAutorizadoException("No hay parqueadero disponible para el vehiculo");
		}
	}

	public void validarEstaRegistrado(int estaRegistrado) {
		if (estaRegistrado >= VEHICULO_REGISTRADO) {
			throw new VehiculoNoAutorizadoException("El vehiculo ya se encuentra registrado en el sistema");
		}
	}


	public void validarNulos(String dato) {
		if ("null".equals(dato) || dato == null || dato.isEmpty()) {
			throw new VehiculoNoAutorizadoException("Hay datos obligatorios que no han sido ingresados");
		}
	}
	
	public void validarCilindrajeMoto(TipoVehiculo tipo, double cilindraje) {
		if (TipoVehiculo.MOTO.equals(tipo) && cilindraje == 0) {
			throw new VehiculoNoAutorizadoException("Para los vehiculos tipo moto el cilindraje es obligatorio");
		}
	}

}
