package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVehiculoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class VehiculoRestController {
	
	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("/vehiculos")
	public List<Vehiculo> index() {
		return vehiculoService.findAll();
	}
	
	@GetMapping("/vehiculos/{id}")
	public Vehiculo show(@PathVariable Long id) {
		return this.vehiculoService.findById(id);
	}
	
	@PostMapping("/vehiculos")
	@ResponseStatus(HttpStatus.CREATED)
	public Vehiculo create(@RequestBody Vehiculo vehiculo) {
		vehiculo.setFechaIngreso(new Date());
		this.vehiculoService.save(vehiculo);
		return vehiculo;
	}
	
	@PutMapping("/vehiculos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Vehiculo update(@RequestBody Vehiculo vehiculo, @PathVariable Long id) {
		Vehiculo currentVehiculo = this.vehiculoService.findById(id);
		currentVehiculo.setPlaca(vehiculo.getPlaca());
		currentVehiculo.setCilindraje(vehiculo.getCilindraje());
		currentVehiculo.setTipo(vehiculo.getTipo());
		this.vehiculoService.save(currentVehiculo);
		return currentVehiculo;
	}

}
