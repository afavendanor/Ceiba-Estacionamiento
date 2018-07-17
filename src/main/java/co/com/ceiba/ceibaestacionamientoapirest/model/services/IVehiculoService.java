package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IVehiculoService {

	public List<Vehiculo> findAll();

	public Vehiculo save(Vehiculo vehiculo);

	public Vehiculo findById(Long id);

	public void validarDisponibilidad(String tipo);

	public void validarHabilitacion(String placa);

	public void estaRegistrado(String placa);

}
