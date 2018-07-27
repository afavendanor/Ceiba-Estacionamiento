package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IVehiculoDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VehiculoServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestVehiculoService {

	private VehiculoServiceImp vehiculoServiceImp;

	@Mock
	private IVehiculoDao iVehiculoDao;

	@Before
	public void setUp() {
		iVehiculoDao = mock(IVehiculoDao.class);
		vehiculoServiceImp = new VehiculoServiceImp(iVehiculoDao);
	}

	@Test
	public void findAll() throws Exception {
		List<Vehiculo> vehiculos = Arrays.asList(new Vehiculo("FDF254", TipoVehiculo.CARRO, 0, new Date()));

		when(vehiculoServiceImp.findAll()).thenReturn(vehiculos);

		assertFalse(vehiculoServiceImp.findAll().isEmpty());

	}

	@Test
	public void update() throws Exception {
		Vehiculo vehiculo = new Vehiculo("FDF254", TipoVehiculo.CARRO, 0, new Date());

		when(vehiculoServiceImp.update(vehiculo)).thenReturn(vehiculo);

		assertNotNull(vehiculoServiceImp.update(vehiculo));

	}

}
