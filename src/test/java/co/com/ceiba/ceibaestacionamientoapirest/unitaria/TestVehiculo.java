package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVehiculoService;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VehiculoServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestVehiculo {

	private boolean respuesta;
	private static final String PLACA_SIN_A = "NWK23D";
	private static final String PLACA_CON_A = "ADN04A";
	private IVehiculoService vehiculoService;

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vehiculoService = new VehiculoServiceImp(vehiculoDao);
	}

	@Test
	public void validarDisponibilidadParqueaderoCarros() {
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO.toString()))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);

		respuesta = vehiculoService.validarDisponibilidad(TipoVehiculo.CARRO.toString());

		assertTrue(respuesta);
	}
	
	@Test
	public void validarNoDisponibilidadParqueaderoCarros() {
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO.toString()))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS);
		try {
			respuesta = vehiculoService.validarDisponibilidad(TipoVehiculo.CARRO.toString());
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}
	}
	
	@Test
	public void validarDisponibilidadParqueaderoMotos() {
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO.toString()))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS - 1);

		respuesta = vehiculoService.validarDisponibilidad(TipoVehiculo.CARRO.toString());

		assertTrue(respuesta);
	}
	
	@Test
	public void validarNoDisponibilidadParqueaderoMotos() {
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.MOTO.toString()))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS);

		try {
			respuesta = vehiculoService.validarDisponibilidad(TipoVehiculo.MOTO.toString());
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}	
	}
	
	@Test
	public void ValidarVehiculoNoRegitrado() {
		Mockito.when(vehiculoDao.estaRegistrado(PLACA_CON_A)).thenReturn(0);

		respuesta = vehiculoService.estaRegistrado(PLACA_CON_A);

		assertFalse(respuesta);

	}
	
	@Test
	public void validarVehiculoRegistrado() {
		Mockito.when(vehiculoDao.estaRegistrado(PLACA_SIN_A)).thenReturn(1);
		
		try {
			respuesta = vehiculoService.estaRegistrado(PLACA_SIN_A);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}
	}

}
