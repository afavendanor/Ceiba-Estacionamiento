package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
