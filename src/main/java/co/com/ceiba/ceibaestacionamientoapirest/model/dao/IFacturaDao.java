package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

	@Modifying
	@Query("UPDATE Vehiculo v SET activo = false WHERE v.id=:id")
	public int cambiarEstadoVehiculo(@Param("id") Long id);
	
}
