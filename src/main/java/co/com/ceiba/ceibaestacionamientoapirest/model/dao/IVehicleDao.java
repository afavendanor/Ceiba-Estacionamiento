package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehicle;

public interface IVehicleDao extends CrudRepository<Vehicle, Long>{

}
