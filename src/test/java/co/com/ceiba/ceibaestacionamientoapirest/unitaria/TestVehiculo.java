package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.com.ceiba.ceibaestacionamientoapirest.databuilder.VehiculoTestDataBluilder;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

public class TestVehiculo {

	@Test
	public void estaRegistrado() {

		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder();

		Vehiculo vehiculo = vehiculoTestDataBluilder.build();

		IVehiculoDao vehiculoDao = mock(IVehiculoDao.class);
		
		when(vehiculoDao.estaRegistrado(vehiculo.getPlaca())).thenReturn(1);

		int estaRegistrado = vehiculoDao.estaRegistrado(vehiculo.getPlaca());

		assertEquals(1, estaRegistrado);
	}
	
	@Test
	public void vehiculosParqueados() {

		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder();

		Vehiculo vehiculo = vehiculoTestDataBluilder.build();

		IVehiculoDao vehiculoDao = mock(IVehiculoDao.class);
		
		when(vehiculoDao.vehiculosParqueados(vehiculo.getTipo())).thenReturn(10);

		int vehiculosParqueados = vehiculoDao.vehiculosParqueados(vehiculo.getTipo());

		assertEquals(10, vehiculosParqueados);

	}
	
	@Test
	public void findAll() {

		IVehiculoDao vehiculoDao = mock(IVehiculoDao.class);
		
		List<Vehiculo> vehiculos  = new ArrayList<Vehiculo>();
		
		when(vehiculoDao.findAll()).thenReturn(vehiculos);

		List<Vehiculo> v = vehiculoDao.findAll();

		assertNotNull(v);

	}
	
}
