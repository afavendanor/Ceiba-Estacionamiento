package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;

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
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 2);
		this.fechaIngreso = cal.getTime();
		this.fechaSalida = new Date();
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
	
	public Factura build() {
		return new Factura(this.placa, this.totalAPagar, this.fechaIngreso, this.fechaSalida);
	}

}
