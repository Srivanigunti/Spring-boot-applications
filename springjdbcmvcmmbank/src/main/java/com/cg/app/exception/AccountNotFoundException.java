package com.cg.app.exception;

public class AccountNotFoundException extends Exception {
	
	private static final long serialVersionUID = 8896660430272351311L;

	public AccountNotFoundException(String message) {
		super(message);
	}
}
