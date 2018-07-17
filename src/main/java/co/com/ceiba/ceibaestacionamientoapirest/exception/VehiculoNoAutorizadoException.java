package co.com.ceiba.ceibaestacionamientoapirest.exception;

public class VehiculoNoAutorizadoException extends RuntimeException{
	
	private static final long serialVersionUID = 1937444020634155410L;
	
	public VehiculoNoAutorizadoException(String mensaje) {
		super(mensaje);
	}

}
