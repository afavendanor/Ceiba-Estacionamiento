package co.com.ceiba.ceibaestacionamientoapirest.integracion;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.com.ceiba.ceibaestacionamientoapirest.CeibaEstacionamientoApiRestApplication;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.FacturaServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CeibaEstacionamientoApiRestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestFacturaRestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FacturaServiceImp facturaServiceImp;

	@Test
	public void listarFacturas() throws Exception {

		List<FacturaEntity> facturas = Arrays.asList(new FacturaEntity("LTY123", 1000, new Date(), new Date()));

		when(facturaServiceImp.findAll()).thenReturn(facturas);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/facturas").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void listarFacturasNotFound() throws Exception {

		List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();

		when(facturaServiceImp.findAll()).thenReturn(facturas);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/facturas").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNoContent()).andReturn();
	}

	@Test
	public void buscarVehiculoNoExistente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/facturas/{id}", -3L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void facturarVehiculo() throws Exception {

		String json = "{ \"placa\": \"MNE58G\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/facturar/{id}", 1L).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}

	@Test
	public void buscarFactura() throws Exception {

		FacturaEntity facturas = new FacturaEntity("LTY123", 1000, new Date(), new Date());

		when(facturaServiceImp.findById(1L)).thenReturn((FacturaEntity) facturas);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/facturas/{id}", 1L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void facturarVehiculoNoIngresado() throws Exception {

		String json = "{ \"placa\": \"MNE58G\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/facturar/{id}", -3L).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound());
	}

}
