package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Long>{

}
