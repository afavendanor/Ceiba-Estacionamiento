package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Parqueadero;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IFacturaDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

@Service
public class FacturaServiceImp implements IFacturaService {

	private IFacturaDao facturaDao;
	Parqueadero parqueadero = Parqueadero.getInstance();

	@Autowired
	public FacturaServiceImp(IFacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		return facturaDao.findAll();
	}

	@Override
	@Transactional
	public Factura generarFactura(Vehiculo vehiculo) {
		Factura factura = new Factura();
		Date fechaSalida = new Date();
		factura.setFechaIngreso(vehiculo.getFechaIngreso());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setFechaSalida(fechaSalida);
		factura.setTotalAPagar(parqueadero.calcularValorAPagar(vehiculo, fechaSalida));
		return this.facturaDao.save(factura);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura findById(Long id) {
		return this.facturaDao.findById(id).orElse(null);
	}

}
