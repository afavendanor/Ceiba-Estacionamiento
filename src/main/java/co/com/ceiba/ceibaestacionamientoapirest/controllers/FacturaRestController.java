package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IFacturaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IFacturaService facturaService;

	@PostMapping("/factura")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura create(@RequestBody Vehiculo vehiculo) {
		Factura factura = new Factura();
		factura.setFechaIngreso(vehiculo.getFechaIngreso());
		factura.setPlaca(vehiculo.getPlaca());
		factura.setFechaSalida(new Date());
		facturaService.valorAPagar(vehiculo, factura.getFechaSalida());
		this.facturaService.save(factura);
		return factura;
	}
}
