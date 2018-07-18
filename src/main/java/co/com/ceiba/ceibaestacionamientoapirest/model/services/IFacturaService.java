package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IFacturaService {

	public Factura save(Factura factura);
	
	public double valorAPagar(Vehiculo vehiculo, Date fechaSalida);
	
	public boolean cambiarEstadoVehiculo(Long id);

}
