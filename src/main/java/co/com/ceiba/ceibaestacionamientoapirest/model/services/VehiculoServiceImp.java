package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	private IVehiculoDao vehiculoDao;

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
		return vehiculoDao.save(vehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo findById(Long id) {
		return vehiculoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean validarDisponibilidad(String tipo) {
		int vehiculosParqueados = vehiculoDao.vehiculosParqueados(tipo);
		if (!((TipoVehiculo.CARRO.toString().equals(tipo) && vehiculosParqueados < Constantes.NUMERO_CARROS_PERMITIDOS)
				|| (TipoVehiculo.MOTO.toString().equals(tipo) && vehiculosParqueados < Constantes.NUMERO_MOTOS_PERMITIDAS))) {
			throw new VehiculoNoAutorizadoException("No hay parqueadero disponible para el vehiculo");
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean validarHabilitacion(String placa) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		if ("A".equalsIgnoreCase(placa.substring(0, 1))
				&& (cal.get(Calendar.DAY_OF_WEEK) != 1 || cal.get(Calendar.DAY_OF_WEEK) != 2)) {			
			throw new VehiculoNoAutorizadoException("El vehiculo no esta autorizado para ingresar");			
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean estaRegistrado(String placa) {
		if (vehiculoDao.estaRegistrado(placa) == 1) {
			throw new VehiculoNoAutorizadoException("El vehiculo ya se encuentra registrado en el sistema");
		}
		return true;
	}

}
