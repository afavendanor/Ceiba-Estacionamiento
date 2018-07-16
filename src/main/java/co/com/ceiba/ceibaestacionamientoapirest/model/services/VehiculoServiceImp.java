package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

@Service
public class VehiculoServiceImp implements IVehiculoService {

	@Autowired
	private IVehiculoDao vehiculoDao;

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
	@Transactional
	public void delete(Long id) {
		vehiculoDao.deleteById(id);
	}

}
