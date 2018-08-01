package co.com.ceiba.ceibaestacionamientoapirest.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public interface IVehiculoRepository extends CrudRepository<VehiculoEntity, Long>{
	
	@Query("select v from VehiculoEntity v where v.activo = TRUE")
	public List<VehiculoEntity> findAll();
	
	@Query("SELECT COUNT(*) FROM VehiculoEntity v WHERE v.tipo=:tipo AND v.activo = TRUE")
    public int vehiculosParqueados(@Param("tipo") TipoVehiculo tipo);
	
	@Query("SELECT COUNT(*) FROM VehiculoEntity v WHERE v.placa=:placa AND v.activo = TRUE")
	public int estaRegistrado(@Param("placa") String placa);

}