package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.Parqueadero;
import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VehiculoServiceImp;

public class TestVehiculo {

	private static final String PLACA_SIN_A = "NWK23D";
	private static final String PLACA_CON_A = "ADN04A";
	Parqueadero parqueadero;
	private boolean respuesta;

	@Mock
	private IVehiculoDao vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		new VehiculoServiceImp(vehiculoDao);
	}

	@Test
	public void validarVehiculoRegistrado() {
		Mockito.when(vehiculoDao.estaRegistrado(PLACA_SIN_A)).thenReturn(0);
		
	}

	@Test
	public void validarVehiculoNoRegistrado() {
		Mockito.when(vehiculoDao.estaRegistrado(PLACA_CON_A)).thenReturn(0);

		respuesta = parqueadero.validarEstaRegistrado(0);

		assertFalse(respuesta);
	}

}
