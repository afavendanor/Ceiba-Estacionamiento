package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.dominio.Vigilante;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@Service
public class VigilanteServiceImp implements IVigilanteService {

	private IVehiculoRepository vehiculoRepository;
	Vigilante vigilante = Vigilante.getInstance();
	Vehiculo vehiculo = Vehiculo.getInstance();

	@Autowired
	public VigilanteServiceImp(IVehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<VehiculoEntity> buscarVehiculos() {
		return vehiculoRepository.findAll();
	}

	@Override
	@Transactional
	public VehiculoEntity guardarVehiculo(VehiculoEntity currentVehiculo) {
		currentVehiculo.setActivo(true);
		currentVehiculo.setFechaIngreso(new Date());
		vigilante.validarNulos(currentVehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(currentVehiculo.getTipo()));
		vigilante.validarEstaRegistrado(vehiculoRepository.estaRegistrado(currentVehiculo.getPlaca()));
		vigilante.validarDisponibilidad(currentVehiculo.getTipo(), vehiculosRegistrados(currentVehiculo.getTipo()));
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(currentVehiculo.getFechaIngreso());
		vehiculo.validarHabilitacion(currentVehiculo.getPlaca(), fecha);
		return vehiculoRepository.save(currentVehiculo);
	}

	@Override
	@Transactional
	public VehiculoEntity actualizarVehiculo(VehiculoEntity vehiculo, VehiculoEntity currentVehiculo) {
		vigilante.validarNulos(vehiculo.getPlaca());
		vigilante.validarNulos(String.valueOf(vehiculo.getTipo()));
		currentVehiculo.setCilindraje(vehiculo.getCilindraje());
		currentVehiculo.setPlaca(vehiculo.getPlaca());
		currentVehiculo.setTipo(vehiculo.getTipo());
		return vehiculoRepository.save(currentVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public VehiculoEntity buscarVehiculo(Long id) {
		return vehiculoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public int vehiculosRegistrados(TipoVehiculo tipo) {
		return vehiculoRepository.vehiculosParqueados(tipo);
	}

	@Override
	public VehiculoEntity cambiarEstadoVehiculo(VehiculoEntity vehiculo) {
		vehiculo.setActivo(false);
		return vehiculoRepository.save(vehiculo);
	}

}
