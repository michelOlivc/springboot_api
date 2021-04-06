package com.br.testeinter.error.exception;

public class TesteInterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TesteInterException() {
		super("Um erro ocorreu. Contate o administrador do sistema.");
	}
	
	public TesteInterException(String msg) {
		super(msg);
	}
}
