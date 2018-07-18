package co.com.ceiba.ceibaestacionamientoapirest.model.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamientoapirest.model.dao.IFacturaDao;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Factura;
import co.com.ceiba.ceibaestacionamientoapirest.model.entity.Vehiculo;
import co.com.ceiba.ceibaestacionamientoapirest.util.Constantes;
import co.com.ceiba.ceibaestacionamientoapirest.util.TipoVehiculo;

@Service
public class FacturaServiceImp implements IFacturaService {

	private static final int SEGUNDOS_HORA = 3600;
	private static final int MAXIMO_HORAS_DIA = 9;
	private static final int HORAS_DIA = 24;

	private IFacturaDao facturaDao;
	
	@Autowired
	public FacturaServiceImp(IFacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}

	@Override
	@Transactional
	public Factura save(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public double valorAPagar(Vehiculo vehiculo, Date fechaSalida) {

		double valorTotal = 0;

		int diferencia = (int) ((fechaSalida.getTime() - vehiculo.getFechaIngreso().getTime()) / 1000);
		int horas = diferencia / SEGUNDOS_HORA != 0 ? diferencia / SEGUNDOS_HORA : 1;
		int dias = horas / HORAS_DIA;
		int horasresiduo = horas % HORAS_DIA;
		double valorHora = TipoVehiculo.MOTO == vehiculo.getTipo() ? Constantes.VALOR_HORA_MOTO
				: Constantes.VALOR_HORA_CARRO;
		double valorDia = TipoVehiculo.CARRO == vehiculo.getTipo() ? Constantes.VALOR_DIA_CARRO
				: Constantes.VALOR_DIA_MOTO;

		if (horasresiduo >= MAXIMO_HORAS_DIA) {
			dias += 1;
		} else {
			double precioHoras = horasresiduo * valorHora;
			valorTotal += precioHoras;
		}

		if (dias > 0) {
			double precioDias = dias * valorDia;
			valorTotal += precioDias;
		}

		if (TipoVehiculo.MOTO == vehiculo.getTipo() && vehiculo.getCilindraje() > Constantes.CILINDRAJE_MOTO_MAXIMO) {
			valorTotal += Constantes.VALOR_ADICIONAL_CILINDRAJE_MOTO;
		}

		return valorTotal;

	}

	@Override
	@Transactional
	public boolean cambiarEstadoVehiculo(Long id) {
		facturaDao.cambiarEstadoVehiculo(id);
		return true;
	}

}
