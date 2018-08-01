package co.com.ceiba.ceibaestacionamientoapirest.databuilder;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;

public class FacturaTestDataBuilder {
	private static final String PLACA = "NWK23D";
	private static final double TOTAL_A_PAGAR = 10000;
	
	private String placa;
	private double totalAPagar;
	private Date fechaIngreso;
	private Date fechaSalida;

	public FacturaTestDataBuilder() {
		this.placa = PLACA;
		this.totalAPagar = TOTAL_A_PAGAR;
		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		this.fechaIngreso = calendar.getTime();
		this.fechaSalida = fechaSalida;
	}
	
	public FacturaTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public FacturaTestDataBuilder conTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
		return this;
	}
	
	public FacturaTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public FacturaTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public FacturaEntity build() {
		return new FacturaEntity(this.placa, this.totalAPagar, this.fechaIngreso, this.fechaSalida);
	}

}
