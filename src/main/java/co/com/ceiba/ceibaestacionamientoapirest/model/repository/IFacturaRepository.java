package co.com.ceiba.ceibaestacionamientoapirest.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;

public interface IFacturaRepository extends CrudRepository<FacturaEntity, Long> {
	
	public List<FacturaEntity> findAll();
	
}
