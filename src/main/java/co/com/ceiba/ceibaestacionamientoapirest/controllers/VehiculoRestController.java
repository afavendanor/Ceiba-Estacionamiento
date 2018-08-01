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

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVigilanteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class VehiculoRestController {

	@Autowired
	private IVigilanteService vehiculoService;

	@GetMapping("/vehiculos")
	public ResponseEntity<List<VehiculoEntity>> listarVehiculos() {
		List<VehiculoEntity> vehiculos  = vehiculoService.buscarVehiculos();
		if (vehiculos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(vehiculos, HttpStatus.OK);
	}
	
	@GetMapping("/vehiculos/{id}")
	public ResponseEntity<VehiculoEntity> buscarVehiculo( @PathVariable Long id) {
		VehiculoEntity vehiculo  = vehiculoService.buscarVehiculo(id);
		if (vehiculo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@GetMapping("/vehiculosSearch/{placa}")
		public ResponseEntity<List<VehiculoEntity>> buscarVehiculos(@PathVariable String placa) {
			List<VehiculoEntity> vehiculos  = vehiculoService.buscarVehiculosPlaca(placa);
			if (vehiculos.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
			return new ResponseEntity<>(vehiculos, HttpStatus.OK);
		}

	@PostMapping("/crearVehiculo")
	public ResponseEntity<VehiculoEntity> crearVehiculo(@RequestBody VehiculoEntity vehiculo) {
		this.vehiculoService.guardarVehiculo(vehiculo);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
		
	@PutMapping("/editarVehiculo/{id}")	
	public ResponseEntity<VehiculoEntity> actualizarVehiculo(@RequestBody VehiculoEntity vehiculo, @PathVariable Long id) {
		VehiculoEntity currentVehiculo = this.vehiculoService.buscarVehiculo(id);
		if (currentVehiculo == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }	
		this.vehiculoService.actualizarVehiculo(vehiculo, currentVehiculo);
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}

}
