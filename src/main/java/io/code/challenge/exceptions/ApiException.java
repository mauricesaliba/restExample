package io.code.challenge.exceptions;

public class ApiException extends Exception{
	
	private static final long serialVersionUID = 9197465114672639712L;

	public ApiException (String msg) {
        super(msg);
    }
}
