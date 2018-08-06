package co.com.ceiba.ceibaestacionamientoapirest.unitaria;


import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Vigilante;
import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestVigilante {

	Vigilante vigilante;
	private static final String PLACA_CON_A = "ADN04A";

	@Mock
	private IVehiculoRepository vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vigilante = Vigilante.getInstance();
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
			assertThat("No hay parqueadero disponible para el vehiculo",is(not( e.getMessage())));
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
			assertThat("No hay parqueadero disponible para el vehiculo",is(not( e.getMessage())));
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
			vigilante.validarNulos("");
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("Hay datos obligatorios que no han sido ingresados", e.getMessage());
		}

	}

	@Test
	public void validarNoCilindrajeMoto() {

		try {
			vigilante.validarCilindrajeMoto(TipoVehiculo.MOTO, 0);
			fail();
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("Para los vehiculos tipo moto el cilindraje es obligatorio", e.getMessage());
		}

	}

}
