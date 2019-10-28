package br.com.casamento.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PessoaControllerExceptionHandler {

	@ExceptionHandler(value = PessoaNotFoundException.class)
	public ResponseEntity<Object> exception(PessoaNotFoundException exe) {
		return new ResponseEntity<>("Desculpe, pessoa não encontrada.", HttpStatus.NOT_FOUND);
	}

}
