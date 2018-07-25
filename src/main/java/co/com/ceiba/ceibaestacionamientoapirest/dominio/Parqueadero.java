package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public final class Parqueadero {

	private static final int SEGUNDOS_HORA = 3600;
	private static final int MAXIMO_HORAS_DIA = 9;
	private static final int HORAS_DIA = 24;

	private static Parqueadero parqueadero;

	private Parqueadero() {

	}

	public static Parqueadero getInstance() {
		if (parqueadero == null) {
			parqueadero = new Parqueadero();
		}
		return parqueadero;
	}

	public double calcularValorAPagar(Vehiculo vehiculo, Date fechaSalida) {

		double valorTotal = 0;

		int diferencia = (int) ((fechaSalida.getTime() - vehiculo.getFechaIngreso().getTime()) / 1000);
		int horas = diferencia / SEGUNDOS_HORA != 0 ? diferencia / SEGUNDOS_HORA : 1;
		int dias = horas / HORAS_DIA;
		int horasresiduo = horas % HORAS_DIA;
		double valorHora = TipoVehiculo.MOTO == vehiculo.getTipo() ? Constantes.VALOR_HORA_MOTO
				: Constantes.VALOR_HORA_CARRO;
		double valorDia = TipoVehiculo.CARRO == vehiculo.getTipo() ? Constantes.VALOR_DIA_CARRO
				: Constantes.VALOR_DIA_MOTO;

		if (horasresiduo >= MAXIMO_HORAS_DIA) {
			dias += 1;
		} else {
			double precioHoras = horasresiduo * valorHora;
			valorTotal += precioHoras;
		}

		if (dias > 0) {
			double precioDias = dias * valorDia;
			valorTotal += precioDias;
		}
		return valorTotal + sumarValorAdicionalCilindraje(vehiculo);
	}

	public double sumarValorAdicionalCilindraje(Vehiculo vehiculo) {
		double valorAdicional = 0;
		if (TipoVehiculo.MOTO == vehiculo.getTipo() && vehiculo.getCilindraje() > Constantes.CILINDRAJE_MOTO_MAXIMO) {
			valorAdicional += Constantes.VALOR_ADICIONAL_CILINDRAJE_MOTO;
		}
		return valorAdicional;
	}

	public boolean validarDisponibilidad(TipoVehiculo tipoVehiculo, int vehiculosParqueados) {
		if (!((TipoVehiculo.CARRO.equals(tipoVehiculo) && vehiculosParqueados < Constantes.NUMERO_CARROS_PERMITIDOS)
				|| (TipoVehiculo.MOTO.equals(tipoVehiculo)
						&& vehiculosParqueados < Constantes.NUMERO_MOTOS_PERMITIDAS))) {
			throw new VehiculoNoAutorizadoException("No hay parqueadero disponible para el vehiculo");
		}
		return true;
	}

	public boolean validarHabilitacion(String placa) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		if (validarPlacaConA(placa)
				&& (cal.get(Calendar.DAY_OF_WEEK) != 1 || cal.get(Calendar.DAY_OF_WEEK) != 2)) {
			throw new VehiculoNoAutorizadoException("El vehiculo no esta autorizado para ingresar");
		}
		return true;
	}

	public boolean validarEstaRegistrado(int estaRegistrado) {
		if (estaRegistrado >= 1) {
			throw new VehiculoNoAutorizadoException("El vehiculo ya se encuentra registrado en el sistema");
		}
		return false;
	}

	public boolean validarPlacaConA(String placa) {
		return "A".equalsIgnoreCase(placa.substring(0, 1));
	}
	
	public boolean validarNulos(String dato) {
		if ("null".equals(dato) || dato == null || dato.isEmpty()) {
			throw new VehiculoNoAutorizadoException("Hay datos obligatorios que no han sido ingresados");
		}
		return true;
	}

}
