package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IVehiculoService;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VehiculoServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestVehiculo {

	private boolean respuesta;
	private static final String PLACA_SIN_A = "NWK23D";
	private static final String PLACA_CON_A = "ABC34A";

	private IVehiculoService vehiculoService;

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vehiculoService = new VehiculoServiceImp(vehiculoDao);
	}

	@Test
	public void vehiculosParqueados() {
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO.toString()))
				.thenReturn((int) Constantes.NUMERO_CARROS_PERMITIDOS - 1);

		respuesta = vehiculoService.validarDisponibilidad(TipoVehiculo.CARRO.toString());

		assertTrue(respuesta);
	}

	@Test
	public void estaRegistrado() {
		Mockito.when(vehiculoDao.estaRegistrado(PLACA_SIN_A)).thenReturn(0);

		respuesta = vehiculoService.estaRegistrado(PLACA_SIN_A);

		assertTrue(respuesta);

	}

}
