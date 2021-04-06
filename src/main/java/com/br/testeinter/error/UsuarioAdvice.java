package com.br.testeinter.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.testeinter.error.exception.TesteInterException;

@ControllerAdvice
public class UsuarioAdvice {

	@ResponseBody
	@ExceptionHandler(TesteInterException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String testeInterHandler(TesteInterException ex) {
		return ex.getMessage();
	}
	
}
