package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVehiculoService;


@RestController
@RequestMapping("/api")
public class VehiculoRestController {
	
	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("/vehiculos")
	public List<Vehiculo> index() {
		return vehiculoService.findAll();
	}

}
