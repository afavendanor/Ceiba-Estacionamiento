package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVigilante;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	private IVehiculoDao vehiculoDao;
	DVigilante vigilante = DVigilante.getInstance();
	DVehiculo dvehiculo = DVehiculo.getInstance();

	@Autowired
	public VehiculoServiceImp(IVehiculoDao vehiculoDao) {
		this.vehiculoDao = vehiculoDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> findAll() {
		return vehiculoDao.findAll();
	}

	@Override
	@Transactional
	public Vehiculo save(Vehiculo vehiculo) {
		vigilante.validarNulos(vehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(vehiculo.getTipo()));
		vigilante.validarEstaRegistrado(vehiculoDao.estaRegistrado(vehiculo.getPlaca()));
		vigilante.validarDisponibilidad(vehiculo.getTipo(), vehiculosRegistrados(vehiculo.getTipo()));
		dvehiculo.validarHabilitacion(vehiculo.getPlaca(), vehiculo.getFechaIngreso());	
		return vehiculoDao.save(vehiculo);
	}
	
	@Override
	@Transactional
	public Vehiculo update(Vehiculo vehiculo) {
		vigilante.validarNulos(vehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(vehiculo.getTipo()));		
		return vehiculoDao.save(vehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo findById(Long id) {
		return vehiculoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int vehiculosRegistrados(TipoVehiculo tipo) {
		return vehiculoDao.vehiculosParqueados(tipo);
	}

}
