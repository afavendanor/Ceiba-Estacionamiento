package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVigilante;
import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestDVigilante {

	DVigilante vigilante;
	private boolean respuesta;
	private static final String PLACA_CON_A = "ADN04A";

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vigilante = DVigilante.getInstance();
	}

	@Test
	public void calcularValorAPagarHoras() {
		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(2000.0, vigilante.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void calcularValorAPagarDias() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 27);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(11000.0, vigilante.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}
	
	@Test
	public void calcularValorAPagarHorasADia() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 35);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(16000.0, vigilante.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}
	
	@Test
	public void calcularValorAPagarDiasMotoCilindrajeMayor() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 51);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);
		vehiculo.setCilindraje(1200);

		assertEquals(11500.0, vigilante.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void validarEstaRegistrado() {

		Mockito.when(vehiculoDao.estaRegistrado(PLACA_CON_A)).thenReturn(1);

		try {
			vigilante.validarEstaRegistrado(1);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}

	}

	@Test
	public void validarDisponibilidadParqueaderoCarros() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);

		try {
			vigilante.validarDisponibilidad(TipoVehiculo.CARRO, (int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);
		} catch (VehiculoNoAutorizadoException e) {
			assertNotEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}
	}

	@Test
	public void validarNoDisponibilidadParqueaderoCarros() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS);
		try {
			vigilante.validarDisponibilidad(TipoVehiculo.CARRO, (int) Constantes.NUMERO_CARROS_PERMITIDOS);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}

	}

	@Test
	public void validarDisponibilidadParqueaderoMotos() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS - 1);

		try {
			vigilante.validarDisponibilidad(TipoVehiculo.CARRO, (int) Constantes.NUMERO_MOTOS_PERMITIDAS - 1);
		} catch (VehiculoNoAutorizadoException e) {
			assertNotEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}

	}

	@Test
	public void validarNoDisponibilidadParqueaderoMotos() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.MOTO))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS);

		try {
			vigilante.validarDisponibilidad(TipoVehiculo.MOTO, (int) Constantes.NUMERO_MOTOS_PERMITIDAS);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}

	}

	@Test
	public void ValidarNulos() {

		try {
			respuesta = vigilante.validarNulos("");
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("Hay datos obligatorios que no han sido ingresados", e.getMessage());
		}

	}

	@Test
	public void ValidarNoNulos() {

		respuesta = vigilante.validarNulos("Test");

		assertTrue(respuesta);

	}

}
