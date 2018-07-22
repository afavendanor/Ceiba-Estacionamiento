package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
	
	public List<Factura> findAll();
	
}
