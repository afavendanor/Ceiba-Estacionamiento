package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IFacturaService;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVehiculoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	@PostMapping("/facturar/{id}")
	public ResponseEntity<Factura> registrarFactura(@PathVariable Long id) {
		Vehiculo currentVehiculo = this.vehiculoService.findById(id);
		if (currentVehiculo == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }		
		currentVehiculo.setActivo(false);
		this.vehiculoService.save(currentVehiculo, "update");		
		return new ResponseEntity<>(this.facturaService.generarFactura(currentVehiculo), HttpStatus.OK);
	}
}
