package co.com.ceiba.ceibaestacionamientoapirest.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.ceiba.ceibaestacionamientoapirest.dominio.DCalculadora;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.model.repository.IVehiculoRepository;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

public class TestDCalculadora {

	DCalculadora calculadora;

	@Mock
	private IVehiculoRepository vehiculoDao;

	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		calculadora = DCalculadora.getInstance();
	}

	@Test
	public void calcularValorAPagarHoras() {
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
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(2000.0, calculadora.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void calcularValorAPagarDias() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 27);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(11000.0, calculadora.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void calcularValorAPagarHorasADia() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 35);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);

		assertEquals(16000.0, calculadora.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

	@Test
	public void calcularValorAPagarDiasMotoCilindrajeMayor() {

		Date fechaSolicitud = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaSolicitud);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date fechaSalida = calendar.getTime();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 51);
		Date fechaIngreso = calendar.getTime();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setPlaca("ASE456");
		vehiculo.setFechaIngreso(fechaIngreso);
		vehiculo.setCilindraje(1200);

		assertEquals(11500.0, calculadora.calcularValorAPagar(vehiculo, fechaSalida), 0.0);
	}

}
