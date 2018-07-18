package co.com.ceiba.ceibaestacionamientoapirest.databuilder;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class VehiculoTest {
	
	private static final String PLACA = "NWK23D";
	private static final int CILINDRAJE = 200;
	private static final TipoVehiculo TIPO = TipoVehiculo.MOTO;
	
	@Test
	public void crearVehiculoTest() {

		/**
		 * Arrange
		 */
		
		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		Date fechaIngreso = calendar.getTime();
		
		VehiculoTestDataBluilder vehiculoTestDataBluilder = new VehiculoTestDataBluilder().conPlaca(PLACA).
				conTipo(TIPO).conActivo(true).conCilindraje(CILINDRAJE).conFechaIngreso(fechaIngreso);
		
		
		/*
		 * Act
		 */
		Vehiculo vehiculo = vehiculoTestDataBluilder.build();

		/**
		 * Asserts
		 */
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(TIPO, vehiculo.getTipo());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		assertEquals(fechaIngreso, vehiculo.getFechaIngreso());
		
	}

}
