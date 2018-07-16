package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Long>{
	
	@Query("select v from Vehiculo v where v.activo = true")
	public List<Vehiculo> findAll();

}
