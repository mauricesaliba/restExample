package io.code.challenge.exceptions;

public class ApiException extends Exception{
    protected int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public int getCode() {
    	return code;
    }
}
