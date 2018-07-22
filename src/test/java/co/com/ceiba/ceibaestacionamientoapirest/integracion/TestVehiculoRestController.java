package co.com.ceiba.ceibaestacionamientoapirest.integracion;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.ceibaestacionamientoapirest.CeibaEstacionamientoApiRestApplication;
import co.com.ceiba.ceibaestacionamientoapirest.databuilder.VehiculoTestDataBluilder;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CeibaEstacionamientoApiRestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")

public class TestVehiculoRestController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void listarVehiculos() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void buscarVehiculo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/1").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void buscarVehiculoNoExistente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", -3L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void agregarVehiculo() throws Exception {
		
		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder();

		Vehiculo vehiculo = vehiculoTestDataBluilder.build();
		
		String json = objectMapper.writeValueAsString(vehiculo);

		this.mockMvc.perform(MockMvcRequestBuilders.post("api/guardarVehiculo").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	/*
	 * @Test public void agregarVehiculoTest() {
	 * 
	 * Vehiculo vehiculo = new
	 * VehiculoTestDataBluilder().conPlaca("XXX12D").conTipo(TipoVehiculo.MOTO)
	 * .conCilindraje(180).conFechaIngreso(new Date()).build();
	 * 
	 * ResponseEntity<Vehiculo> responseEntityIngreso = restTemplate
	 * .postForEntity("http://localhost:3001/api/vehiculo", vehiculo,
	 * Vehiculo.class);
	 * 
	 * assertEquals(HttpStatus.CREATED, responseEntityIngreso.getStatusCode()); }
	 */

	private String toJsonString(Map<String, ?> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
