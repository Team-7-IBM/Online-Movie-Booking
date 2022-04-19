package com.ibm.exception;

public class SeatNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public SeatNotFoundException() {
	}

	public SeatNotFoundException(String message) {
		super(message);
	}
}
