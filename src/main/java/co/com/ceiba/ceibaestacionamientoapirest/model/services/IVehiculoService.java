package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IVehiculoService {
	
	public List<Vehiculo> findAll();

}
