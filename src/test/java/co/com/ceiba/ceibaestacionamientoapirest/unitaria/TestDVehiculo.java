package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DVehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;

public class TestDVehiculo {

	DVehiculo vehiculo;
	private boolean respuesta;
	private static final String PLACA_CON_A = "ADN04A";
	private static final String PLACA_SIN_A = "NWK23D";

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vehiculo = DVehiculo.getInstance();
	}

	@Test
	public void validarPlacaConA() {

		respuesta = vehiculo.validarPlacaConA(PLACA_CON_A);

		assertTrue(respuesta);

	}

	@Test
	public void validarPlacaSinA() {

		respuesta = vehiculo.validarPlacaConA(PLACA_SIN_A);

		assertFalse(respuesta);

	}

	@Test
	public void validarHabilitacion() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringFechaConHora = "2018-07-22 15:03:23";
		Date fecha = sdf.parse(stringFechaConHora);
		try {
			vehiculo.validarHabilitacion(PLACA_CON_A, fecha);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo no esta autorizado para ingresar", e.getMessage());

		}

	}

	@Test
	public void validarNoHabilitacion() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringFechaConHora = "2018-07-25 15:03:23";
		Date fecha = sdf.parse(stringFechaConHora);
		try {
			vehiculo.validarHabilitacion(PLACA_CON_A, fecha);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo no esta autorizado para ingresar", e.getMessage());

		}
	}

}
