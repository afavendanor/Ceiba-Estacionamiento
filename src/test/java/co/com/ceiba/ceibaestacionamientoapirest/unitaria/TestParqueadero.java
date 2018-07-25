package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Parqueadero;
import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestParqueadero {

	Parqueadero parqueadero;
	private boolean respuesta;
	private static final String PLACA_CON_A = "ADN04A";
	private static final String PLACA_SIN_A = "NWK23D";

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		parqueadero = Parqueadero.getInstance();
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

		assertEquals(2000.0, parqueadero.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
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

		assertEquals(11000.0, parqueadero.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void ValidarVehiculoNoRegitrado() {

		Mockito.when(vehiculoDao.estaRegistrado(PLACA_CON_A)).thenReturn(0);

		respuesta = parqueadero.validarEstaRegistrado(0);

		assertFalse(respuesta);

	}

	@Test
	public void validarPlacaConA() {

		respuesta = parqueadero.validarPlacaConA(PLACA_CON_A);

		assertTrue(respuesta);

	}

	@Test
	public void validarPlacaSinA() {

		respuesta = parqueadero.validarPlacaConA(PLACA_SIN_A);

		assertFalse(respuesta);

	}

	@Test
	public void validarDisponibilidadParqueaderoCarros() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);

		respuesta = parqueadero.validarDisponibilidad(TipoVehiculo.CARRO,
				(int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);

		assertTrue(respuesta);

	}

	@Test
	public void validarNoDisponibilidadParqueaderoCarros() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS);
		try {
			respuesta = parqueadero.validarDisponibilidad(TipoVehiculo.CARRO,
					(int) Constantes.NUMERO_CARROS_PERMITIDOS);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}

	}

	@Test
	public void validarDisponibilidadParqueaderoMotos() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS - 1);

		respuesta = parqueadero.validarDisponibilidad(TipoVehiculo.CARRO, (int) Constantes.NUMERO_MOTOS_PERMITIDAS - 1);

		assertTrue(respuesta);

	}

	@Test
	public void validarNoDisponibilidadParqueaderoMotos() {

		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.MOTO))
				.thenReturn((int) Constantes.NUMERO_MOTOS_PERMITIDAS);

		try {
			respuesta = parqueadero.validarDisponibilidad(TipoVehiculo.MOTO, (int) Constantes.NUMERO_MOTOS_PERMITIDAS);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}

	}

	@Test
	public void validarHabilitacion() {

		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			respuesta = parqueadero.validarHabilitacion(PLACA_CON_A);
			if ((cal.get(Calendar.DAY_OF_WEEK) != 1 || cal.get(Calendar.DAY_OF_WEEK) != 2)) {
				assertFalse(respuesta);
			} else {
				fail();
			}
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo no esta autorizado para ingresar", e.getMessage());
		}

	}

	@Test
	public void validarEstaRegistrado() {

		try {
			respuesta = parqueadero.validarEstaRegistrado(1);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}

	}

	@Test
	public void ValidarNulos() {

		try {
			respuesta = parqueadero.validarNulos("");
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("Hay datos obligatorios que no han sido ingresados", e.getMessage());
		}

	}

	@Test
	public void ValidarNoNulos() {

		respuesta = parqueadero.validarNulos("Test");

		assertTrue(respuesta);

	}

}
