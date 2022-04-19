package com.ibm.exception;

public class TheatreNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TheatreNotFoundException(String msg) {
		super(msg);
	}
}
