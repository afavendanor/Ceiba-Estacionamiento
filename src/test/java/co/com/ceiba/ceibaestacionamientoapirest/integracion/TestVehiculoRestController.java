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
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.VehiculoEntity;
import co.com.ceiba.ceibaestacionamientoapirest.model.services.VigilanteServiceImp;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CeibaEstacionamientoApiRestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")

public class TestVehiculoRestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VigilanteServiceImp vehiculoServiceImp;

	@Test
	public void listarVehiculos() throws Exception {
		
		List<VehiculoEntity> vehiculos = Arrays.asList(new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date()));

		when(vehiculoServiceImp.buscarVehiculos()).thenReturn(vehiculos);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}
	
	@Test
	public void listarVehiculosNotFound() throws Exception {
		
		List<VehiculoEntity> vehiculo = new ArrayList<VehiculoEntity>();

		when(vehiculoServiceImp.buscarVehiculos()).thenReturn(vehiculo);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNoContent()).andReturn();

	}
	

	@Test
	public void buscarVehiculo() throws Exception {
		
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date());

		when(vehiculoServiceImp.buscarVehiculo(1L)).thenReturn(vehiculo);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", 1L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void buscarVehiculoNoExistente() throws Exception {
		
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date());

		when(vehiculoServiceImp.buscarVehiculo(1L)).thenReturn(vehiculo);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculos/{id}", -3L).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void crearVehiculo() throws Exception {

		String json = "{ \"placa\": \"MNE58G\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/crearVehiculo").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}

	@Test
	public void modificarVehiculo() throws Exception {
		
		VehiculoEntity vehiculo = new VehiculoEntity("FDF254", TipoVehiculo.CARRO, 0, new Date());

		when(vehiculoServiceImp.buscarVehiculo(1L)).thenReturn(vehiculo);

		String json = "{ \"placa\": \"MNE58A\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/editarVehiculo/{id}", 1L).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void modificarVehiculoNotFound() throws Exception {
		
		VehiculoEntity vehiculo = new VehiculoEntity();

		when(vehiculoServiceImp.buscarVehiculo(1L)).thenReturn(vehiculo);

		String json = "{ \"placa\": \"MNE58A\" , \"tipo\": \"MOTO\",\"activo\": \"true\", \"cilindraje\": \"1200\" }";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/editarVehiculo/{id}", -3L).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound());
	}

}
