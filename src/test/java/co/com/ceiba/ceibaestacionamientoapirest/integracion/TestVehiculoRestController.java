package co.com.ceiba.ceibaestacionamientoapirest.integracion;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import co.com.ceiba.ceibaestacionamientoapirest.databuilder.VehiculoTestDataBluilder;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

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
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/1").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void buscarVehiculoNoExistente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", -3L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void agregarVehiculo() throws Exception {
		Vehiculo vehiculo = new VehiculoTestDataBluilder().conPlaca("XXX12D").conTipo(TipoVehiculo.MOTO)
				.conCilindraje(180).conFechaIngreso(new Date()).build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/guardarVehiculo", vehiculo).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andReturn();
	}

	/*
	@Test
	public void agregarVehiculoTest() {

		Vehiculo vehiculo = new VehiculoTestDataBluilder().conPlaca("XXX12D").conTipo(TipoVehiculo.MOTO)
				.conCilindraje(180).conFechaIngreso(new Date()).build();

		ResponseEntity<Vehiculo> responseEntityIngreso = restTemplate
				.postForEntity("http://localhost:3001/api/vehiculo", vehiculo, Vehiculo.class);

		assertEquals(HttpStatus.CREATED, responseEntityIngreso.getStatusCode());
	}
	*/

}
