package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.com.ceiba.ceibaestacionamientoapirest.databuilder.VehiculoTestDataBluilder;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;

public class TestVehiculoRepository {

	@Test
	public void estaRegistrado() {

		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder();

		VehiculoEntity vehiculo = vehiculoTestDataBluilder.build();

		IVehiculoRepository vehiculoRepository = mock(IVehiculoRepository.class);
		
		when(vehiculoRepository.estaRegistrado(vehiculo.getPlaca())).thenReturn(1);

		int estaRegistrado = vehiculoRepository.estaRegistrado(vehiculo.getPlaca());

		assertEquals(1, estaRegistrado);
	}
	
	@Test
	public void vehiculosParqueados() {

		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder();

		VehiculoEntity vehiculo = vehiculoTestDataBluilder.build();

		IVehiculoRepository vehiculoRepository = mock(IVehiculoRepository.class);
		
		when(vehiculoRepository.vehiculosParqueados(vehiculo.getTipo())).thenReturn(10);

		int vehiculosParqueados = vehiculoRepository.vehiculosParqueados(vehiculo.getTipo());

		assertEquals(10, vehiculosParqueados);

	}
	
	@Test
	public void findAll() {

		IVehiculoRepository vehiculoRepository = mock(IVehiculoRepository.class);
		
		List<VehiculoEntity> vehiculos  = new ArrayList<VehiculoEntity>();
		
		when(vehiculoRepository.findAll()).thenReturn(vehiculos);

		List<VehiculoEntity> v = vehiculoRepository.findAll();

		assertNotNull(v);

	}
	
}
