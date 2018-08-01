package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IFacturaRepository;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.FacturaServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestFacturaService {

	private FacturaServiceImp facturaServiceImp;

	@Mock
	private IFacturaRepository iFacturaloDao;

	@Before
	public void setUp() {
		iFacturaloDao = mock(IFacturaRepository.class);
		facturaServiceImp = new FacturaServiceImp(iFacturaloDao);
	}

	@Test
	public void findAll() throws Exception {
		List<FacturaEntity> facturas = Arrays.asList(new FacturaEntity("FDF254", 1000, new Date(), new Date()));

		when(iFacturaloDao.findAll()).thenReturn(facturas);

		assertFalse(facturaServiceImp.findAll().isEmpty());

	}

	@Test
	public void findById() throws Exception {
		Optional<FacturaEntity> factura = Optional.of(new FacturaEntity("FDF254", 1000, new Date(), new Date()));

		when(iFacturaloDao.findById(1L)).thenReturn(factura);

		assertNotNull(facturaServiceImp.findById(1L));

	}
	
	
	@Test
	public void generarFactura() throws Exception {
			
		FacturaEntity factura = new FacturaEntity("FDF254", 1000, new Date(), new Date());
		
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0,  new Date());

		when(iFacturaloDao.save(Mockito.any(FacturaEntity.class))).thenReturn(factura);
		
		FacturaEntity fact =  facturaServiceImp.generarFactura(vehiculo);
		
		System.err.println("------->" + facturaServiceImp.generarFactura(vehiculo));
		
		assertNotNull(fact);

	}


}
