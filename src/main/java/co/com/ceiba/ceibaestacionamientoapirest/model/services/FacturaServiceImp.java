package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DCalculadora;
import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVigilante;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IFacturaRepository;

@Service
public class FacturaServiceImp implements IFacturaService {

	private IFacturaRepository facturaRepository;
	DVigilante vigilante = DVigilante.getInstance();
	DCalculadora calculadora = DCalculadora.getInstance();
	
	@Autowired
	public FacturaServiceImp(IFacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		return facturaRepository.findAll();
	}

	@Override
	@Transactional
	public Factura generarFactura(Vehiculo vehiculo) {
		
		Factura factura = new Factura();
		Date fechaSalida = new Date();		
		factura.setFechaIngreso(vehiculo.getFechaIngreso());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setFechaSalida(fechaSalida);		
		factura.setTotalAPagar(calculadora.calcularValorAPagar(vehiculo, fechaSalida));

		return this.facturaRepository.save(factura);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura findById(Long id) {
		return this.facturaRepository.findById(id).orElse(null);
	}

}
