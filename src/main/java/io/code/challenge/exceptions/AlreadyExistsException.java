package io.code.challenge.exceptions;

public class AlreadyExistsException extends ApiException {
    
    public AlreadyExistsException () {
        super(409, "Already exists.");
    }
}
