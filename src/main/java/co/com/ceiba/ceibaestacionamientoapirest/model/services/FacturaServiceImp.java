package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;

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
	@Transactional
	public Factura generarFactura(Vehiculo vehiculo) {
		Factura factura = new Factura();
		factura.setFechaIngreso(vehiculo.getFechaIngreso());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setFechaSalida(new Date());
		factura.setTotalAPagar(parqueadero.calcularValorAPagar(vehiculo, factura.getFechaSalida()));
		return this.facturaDao.save(factura);
	}

}
