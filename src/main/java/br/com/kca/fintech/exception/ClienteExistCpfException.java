package br.com.kca.fintech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteExistCpfException extends Exception {
	
	public ClienteExistCpfException(Long id) {
		super(String.format("CPF já consta na base de dados", id));
	}

}
