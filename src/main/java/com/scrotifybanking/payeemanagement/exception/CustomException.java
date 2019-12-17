package com.scrotifybanking.payeemanagement.exception;

public class CustomException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

}
