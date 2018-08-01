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

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VigilanteServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestVigilanteService {

	private VigilanteServiceImp vehiculoServiceImp;

	@Mock
	private IVehiculoRepository iVehiculoDao;

	@Before
	public void setUp() {
		iVehiculoDao = mock(IVehiculoRepository.class);
		vehiculoServiceImp = new VigilanteServiceImp(iVehiculoDao);
	}

	@Test
	public void findAll() throws Exception {
		List<VehiculoEntity> vehiculos = Arrays.asList(new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date()));

		when(vehiculoServiceImp.buscarVehiculos()).thenReturn(vehiculos);

		assertFalse(vehiculoServiceImp.buscarVehiculos().isEmpty());

	}

	@Test
	public void update() throws Exception {
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date());
		VehiculoEntity currentVehiculo = new VehiculoEntity("FDF254", TipoVehiculo.MOTO, 1200, new Date());

		when(vehiculoServiceImp.actualizarVehiculo(vehiculo, currentVehiculo)).thenReturn(currentVehiculo);

		assertNotNull(vehiculoServiceImp.actualizarVehiculo(vehiculo, currentVehiculo));

	}

	
	@Test
	public void save() throws Exception {
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, null);

		when(vehiculoServiceImp.guardarVehiculo(vehiculo)).thenReturn(vehiculo);

		assertNotNull(vehiculoServiceImp.guardarVehiculo(vehiculo));

	}
	
}
