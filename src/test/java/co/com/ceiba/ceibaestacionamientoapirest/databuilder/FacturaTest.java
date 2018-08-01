package co.com.ceiba.ceibaestacionamientoapirest.databuilder;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.ceibaestacionamientoapirest.model.entity.FacturaEntity;

public class FacturaTest {

	private static final String PLACA = "NWK23D";
	private static final double TOTAL_A_PAGAR = 2000;

	@Test
	public void crearFacturaTest() {

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
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		Date fechaIngreso = calendar.getTime();

		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().conPlaca(PLACA)
				.conTotalAPagar(TOTAL_A_PAGAR).conFechaIngreso(fechaIngreso).conFechaSalida(fechaSalida);
		/*
		 * Act
		 */
		FacturaEntity factura = facturaTestDataBuilder.build();

		/**
		 * Asserts
		 */
		assertEquals(PLACA, factura.getPlaca());
		Assert.assertEquals(TOTAL_A_PAGAR, factura.getTotalAPagar(), 0.00);
		assertEquals(fechaIngreso, factura.getFechaIngreso());
		assertEquals(fechaSalida, factura.getFechaSalida());

	}
}
