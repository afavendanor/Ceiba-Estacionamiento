package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IFacturaDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.FacturaServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.IFacturaService;

public class TestFactura {

	private static final Long ID_VEHICULO = 1L;
	boolean respuesta;

	private IFacturaService facturaService;

	@Mock
	private IFacturaDao facturaDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		facturaService = new FacturaServiceImp(facturaDao);
	}

	@Test
	public void cambiarEstadoVehiculo() {
		Mockito.when(facturaDao.cambiarEstadoVehiculo(ID_VEHICULO)).thenReturn(1);
		
		respuesta = facturaService.cambiarEstadoVehiculo(ID_VEHICULO);
		
		assertTrue(respuesta);

	}

}
