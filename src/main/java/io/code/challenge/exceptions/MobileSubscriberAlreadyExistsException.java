package io.code.challenge.exceptions;

public class MobileSubscriberAlreadyExistsException extends ApiException {
    
	private static final long serialVersionUID = -472736781213568893L;

	public MobileSubscriberAlreadyExistsException () {
        super("Mobile Subscriber Already exists.");
    }
}
