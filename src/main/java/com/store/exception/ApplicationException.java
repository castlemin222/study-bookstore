package com.store.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 2459214783459665963L;
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
