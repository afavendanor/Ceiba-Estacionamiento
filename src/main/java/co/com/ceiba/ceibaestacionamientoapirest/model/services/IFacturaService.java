package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IFacturaService {
	
	public Factura generarFactura(Vehiculo vehiculo);

}
