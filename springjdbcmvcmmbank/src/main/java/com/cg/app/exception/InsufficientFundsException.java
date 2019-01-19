package com.cg.app.exception;
public class InsufficientFundsException extends RuntimeException {

	
	private static final long serialVersionUID = -2124118365029427055L;

	public InsufficientFundsException(String message) {
		super(message);
	}

}
