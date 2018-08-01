package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public interface IVigilanteService {

	public List<VehiculoEntity> buscarVehiculos();

	public VehiculoEntity guardarVehiculo(VehiculoEntity vehiculo);
	
	public VehiculoEntity actualizarVehiculo(VehiculoEntity vehiculo, VehiculoEntity searchVehiculo);
	
	public VehiculoEntity buscarVehiculo(Long id);
	
	public List<VehiculoEntity> buscarVehiculosPlaca(String placa);
	
	public int vehiculosRegistrados(TipoVehiculo tipo);
	
	public VehiculoEntity cambiarEstadoVehiculo(VehiculoEntity vehiculo);

}
