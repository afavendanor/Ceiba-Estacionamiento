package co.com.ceiba.ceibaestacionamientoapirest.integracion;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.com.ceiba.ceibaestacionamientoapirest.CeibaEstacionamientoApiRestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CeibaEstacionamientoApiRestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")

public class TestVehiculoRestController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void listarVehiculos() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void buscarVehiculo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", 1L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void buscarVehiculoNoExistente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", -3L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void agregarVehiculo() throws Exception {
		
		String json = "{ \"placa\": \"MNE58G\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/guardarVehiculo").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}





}
