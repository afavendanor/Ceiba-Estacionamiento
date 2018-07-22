package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IFacturaService {
	
	public Factura generarFactura(Vehiculo vehiculo);
	
	public List<Factura> findAll();

}
