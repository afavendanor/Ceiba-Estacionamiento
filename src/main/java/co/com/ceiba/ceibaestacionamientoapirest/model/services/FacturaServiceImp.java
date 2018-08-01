package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Calculadora;
import co.com.ceiba.ceibaestacionamientoapirest.dominio.Vigilante;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IFacturaRepository;

@Service
public class FacturaServiceImp implements IFacturaService {

	private IFacturaRepository facturaRepository;
	Vigilante vigilante = Vigilante.getInstance();
	Calculadora calculadora = Calculadora.getInstance();
	
	@Autowired
	public FacturaServiceImp(IFacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FacturaEntity> findAll() {
		return facturaRepository.findAll();
	}

	@Override
	@Transactional
	public FacturaEntity generarFactura(VehiculoEntity vehiculo) {
		
		FacturaEntity factura = new FacturaEntity();
		Date fechaSalida = new Date();		
		factura.setFechaIngreso(vehiculo.getFechaIngreso());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setFechaSalida(fechaSalida);		
		factura.setTotalAPagar(calculadora.calcularValorAPagar(vehiculo, fechaSalida));

		return this.facturaRepository.save(factura);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public FacturaEntity findById(Long id) {
		return this.facturaRepository.findById(id).orElse(null);
	}

}
