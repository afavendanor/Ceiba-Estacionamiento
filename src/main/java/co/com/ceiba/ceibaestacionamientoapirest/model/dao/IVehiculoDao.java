package co.com.ceiba.ceibaestacionamientoapirest.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Long>{
	
	@Query("select v from Vehiculo v where v.activo = TRUE")
	public List<Vehiculo> findAll();
	
	@Query("SELECT COUNT(*) FROM Vehiculo v WHERE v.tipo=:tipo AND v.activo = TRUE")
    public int vehiculosParqueados(@Param("tipo") TipoVehiculo tipo);
	
	@Query("SELECT COUNT(*) FROM Vehiculo v WHERE v.placa=:placa AND v.activo = TRUE")
	public int estaRegistrado(@Param("placa") String placa);

}