package co.com.ceiba.ceibaestacionamientoapirest.dominio;

import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public final class Calculadora {
	
	private static Calculadora calculadora;

	private Calculadora() {

	}

	public static Calculadora getInstance() {
		if (calculadora == null) {
			calculadora = new Calculadora();
		}
		return calculadora;
	}
	

	private static final int MAXIMO_HORAS_DIA = 9;
	private static final int HORAS_DIA = 24;
	
	public double calcularValorAPagar(VehiculoEntity vehiculo, Date fechaSalida) {

		double valorTotal = 0;

		int horas = Calendario.getInstance().retornarHorasParqueo(vehiculo.getFechaIngreso(), fechaSalida);
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
	
	public double sumarValorAdicionalCilindraje(VehiculoEntity vehiculo) {
		double valorAdicional = 0;
		if (TipoVehiculo.MOTO == vehiculo.getTipo() && vehiculo.getCilindraje() > Constantes.CILINDRAJE_MOTO_MAXIMO) {
			valorAdicional += Constantes.VALOR_ADICIONAL_CILINDRAJE_MOTO;
		}
		return valorAdicional;
	}
	
}
