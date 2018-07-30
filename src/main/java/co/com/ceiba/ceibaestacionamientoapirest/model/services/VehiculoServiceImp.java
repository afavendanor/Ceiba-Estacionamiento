package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVigilante;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	private IVehiculoRepository vehiculoRepository;
	DVigilante vigilante = DVigilante.getInstance();
	DVehiculo dvehiculo = DVehiculo.getInstance();

	@Autowired
	public VehiculoServiceImp(IVehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> findAll() {
		return vehiculoRepository.findAll();
	}

	@Override
	@Transactional
	public Vehiculo save(Vehiculo vehiculo) {
		vigilante.validarNulos(vehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(vehiculo.getTipo()));
		vigilante.validarEstaRegistrado(vehiculoRepository.estaRegistrado(vehiculo.getPlaca()));
		vigilante.validarDisponibilidad(vehiculo.getTipo(), vehiculosRegistrados(vehiculo.getTipo()));
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(vehiculo.getFechaIngreso());
		dvehiculo.validarHabilitacion(vehiculo.getPlaca(), fecha);	
		return vehiculoRepository.save(vehiculo);
	}
	
	@Override
	@Transactional
	public Vehiculo update(Vehiculo vehiculo) {
		vigilante.validarNulos(vehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(vehiculo.getTipo()));		
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo findById(Long id) {
		return vehiculoRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int vehiculosRegistrados(TipoVehiculo tipo) {
		return vehiculoRepository.vehiculosParqueados(tipo);
	}

}
