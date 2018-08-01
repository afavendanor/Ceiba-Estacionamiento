package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IFacturaService;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVigilanteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private IVigilanteService vehiculoService;

	@GetMapping("/facturas")
	public ResponseEntity<List<FacturaEntity>> listarFacturas() {
		List<FacturaEntity> facturas = facturaService.findAll();
		if (facturas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(facturas, HttpStatus.OK);
	}

	@PostMapping("/facturar/{id}")
	public ResponseEntity<FacturaEntity> registrarFactura(@PathVariable Long id) {
		VehiculoEntity currentVehiculo = this.vehiculoService.buscarVehiculo(id);
		if (currentVehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.vehiculoService.cambiarEstadoVehiculo(currentVehiculo);
		return new ResponseEntity<>(this.facturaService.generarFactura(currentVehiculo), HttpStatus.CREATED);
	}
	
	@GetMapping("/facturas/{id}")
	public ResponseEntity<FacturaEntity> buscarFactura( @PathVariable Long id) {
		FacturaEntity factura  = facturaService.findById(id);
		if (factura == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(factura, HttpStatus.OK);
	}
}
