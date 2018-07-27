package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
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
	public void validarHabilitacion() {

		try {
			Date fecha = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			vehiculo.validarHabilitacion(PLACA_CON_A, fecha);
			if ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					|| cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)) {
				assertFalse(respuesta);
			} else {
				fail();
			}
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo no esta autorizado para ingresar", e.getMessage());
		}

	}

}
