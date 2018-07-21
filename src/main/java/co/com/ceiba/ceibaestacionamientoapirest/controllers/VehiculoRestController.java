package co.com.ceiba.ceibaestacionamientoapirest.controllers;

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
		List<Vehiculo> vehiculos  = vehiculoService.findAll();
		if (vehiculos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(vehiculos, HttpStatus.OK);
	}
	
	@GetMapping("/vehiculos/{id}")
	public ResponseEntity<Vehiculo> buscarVehiculo( @PathVariable Long id) {
		Vehiculo vehiculo  = vehiculoService.findById(id);
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}

	@PostMapping("/guardarVehiculo")
	public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
		this.vehiculoService.save(vehiculo, "save");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/editarVehiculo/{id}")	
	public ResponseEntity<Vehiculo> actualizarVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable Long id) {
		Vehiculo currentVehiculo = this.vehiculoService.findById(id);
		if (currentVehiculo == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }	
		currentVehiculo.setCilindraje(vehiculo.getCilindraje());
		currentVehiculo.setPlaca(vehiculo.getPlaca());
		currentVehiculo.setTipo(vehiculo.getTipo());
		this.vehiculoService.save(currentVehiculo, "update");
		return new ResponseEntity<>(currentVehiculo, HttpStatus.OK);
	}

}
