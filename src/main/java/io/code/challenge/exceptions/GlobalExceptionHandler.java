package io.code.challenge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({ MobileSubscriberAlreadyExistsException.class, MobileSubscriberNotFoundException.class })
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

		if (ex instanceof MobileSubscriberAlreadyExistsException) {

			HttpStatus status = HttpStatus.CONFLICT;
			MobileSubscriberAlreadyExistsException msaee = (MobileSubscriberAlreadyExistsException) ex;
			return handleMobileSubscriberAlreadyExistsException(msaee, status, headers, request);
		} else if (ex instanceof MobileSubscriberNotFoundException) {

			HttpStatus status = HttpStatus.NOT_FOUND;
		    MobileSubscriberNotFoundException msnfe = (MobileSubscriberNotFoundException) ex;
			return handleMobileSubscriberNotFoundException(msnfe, status, headers, request);

		} else if (ex instanceof ApiException) {

			HttpStatus status = HttpStatus.BAD_REQUEST;
			MobileSubscriberAlreadyExistsException ae = (MobileSubscriberAlreadyExistsException) ex;
			return handleMobileSubscriberAlreadyExistsException(ae, status, headers, request);
		}
		
		
		else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(ex, null, headers, status, request);
		}
    }

    /** Customize the response for MobileSubscriberAlreadyExistsException. */
    protected ResponseEntity<String> handleMobileSubscriberAlreadyExistsException(MobileSubscriberAlreadyExistsException ex, HttpStatus status, HttpHeaders headers, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }
    
    
    /** Customize the response for MobileSubscriberNotFoundException. */
    protected ResponseEntity<String> handleMobileSubscriberNotFoundException(MobileSubscriberNotFoundException ex, HttpStatus status, HttpHeaders headers, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }
    
    /** Customize the response for generic Api Exceptions. */
    protected ResponseEntity<String> handleApiException (Exception ex, HttpStatus status, HttpHeaders headers, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    /** A single place to customize the response body of all Exception types. */
    protected ResponseEntity<String> handleExceptionInternal(Exception ex, @Nullable String body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<String>(body, headers, status);
    }
}