package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public interface IVehiculoService {

	public List<Vehiculo> findAll();

	public Vehiculo save(Vehiculo vehiculo, String accion);
	
	public Vehiculo findById(Long id);
	
	public int vehiculosRegistrados(TipoVehiculo tipo);

}
