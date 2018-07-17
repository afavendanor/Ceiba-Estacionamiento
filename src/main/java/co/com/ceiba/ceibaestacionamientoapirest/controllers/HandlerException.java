package co.com.ceiba.ceibaestacionamientoapirest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import co.com.ceiba.ceibaestacionamientoapirest.exception.VehiculoNoAutorizadoException;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler({ VehiculoNoAutorizadoException.class })
	public ResponseEntity<Object> handlerVehiculoNoAutorizado(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Object> handlerErrorInterno(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
