package io.code.challenge.exceptions;

public class NotFoundException extends ApiException {

	public NotFoundException() {
		super(404, "Not found.");
	}
}
