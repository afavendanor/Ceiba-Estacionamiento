package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<Vehiculo>> listarVehiculos() {
		List<Vehiculo> vehiculos;
		vehiculos = vehiculoService.findAll();
		if (vehiculos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(vehiculos, HttpStatus.OK);
	}

	@PostMapping("/vehiculos")
	public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
		vehiculo.setFechaIngreso(new Date());
		vehiculoService.estaRegistrado(vehiculo.getPlaca());
		this.vehiculoService.save(vehiculo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/vehiculos/{id}")
	public ResponseEntity<Vehiculo> cambiarEstadoVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable Long id) {
		Vehiculo currentVehiculo = this.vehiculoService.findById(id);
		if (currentVehiculo == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }		
		currentVehiculo.setPlaca(vehiculo.getPlaca());
		currentVehiculo.setCilindraje(vehiculo.getCilindraje());
		currentVehiculo.setTipo(vehiculo.getTipo());
		currentVehiculo.setActivo(vehiculo.isActivo());
		this.vehiculoService.save(currentVehiculo);
		return new ResponseEntity<>(currentVehiculo, HttpStatus.OK);
	}

}
