package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class VehiculoTestDataBluilder {

	private static final String PLACA = "NWK23D";
	private static final int CILINDRAJE = 200;
	private static final TipoVehiculo TIPO = TipoVehiculo.MOTO;

	private String placa;
	private TipoVehiculo tipo;
	private int cilindraje;
	private boolean activo;
	private Date fechaIngreso;

	public VehiculoTestDataBluilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.activo = true;
		this.cilindraje = CILINDRAJE;
		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		this.fechaIngreso = calendar.getTime();
	}

	public VehiculoTestDataBluilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestDataBluilder conTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoTestDataBluilder conActivo(boolean activo) {
		this.activo = activo;
		return this;
	}

	public VehiculoTestDataBluilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoTestDataBluilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.tipo, this.cilindraje , this.fechaIngreso);
	}

}
