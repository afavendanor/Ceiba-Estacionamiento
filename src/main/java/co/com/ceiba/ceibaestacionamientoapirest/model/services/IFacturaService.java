package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;

public interface IFacturaService {
	
	public FacturaEntity generarFactura(VehiculoEntity vehiculo);
	
	public List<FacturaEntity> findAll();
	
	public FacturaEntity findById(Long id);

}
