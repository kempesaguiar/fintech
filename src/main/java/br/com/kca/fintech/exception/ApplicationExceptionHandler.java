package br.com.kca.fintech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity handlerException(Exception e) {
		return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClienteExistCpfException.class)
	public ResponseEntity handlerCpfExistException(Exception e) {
		return new ResponseEntity("CPF já consta na nossa base", HttpStatus.NOT_ACCEPTABLE);
	}

}
