package io.code.challenge.exceptions;

public class MobileSubscriberNotFoundException extends ApiException {

	private static final long serialVersionUID = -1636969514753602519L;

	public MobileSubscriberNotFoundException() {
		super( "Mobile subscriber was not found.");
	}
}
