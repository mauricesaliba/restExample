package io.code.challenge.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.AlreadyExistsException;
import io.code.challenge.exceptions.NotFoundException;
import io.code.challenge.model.DtoConverter;
import io.code.challenge.model.MobileSubscriberDto;
import io.code.challenge.service.IMobileSubscriberService;
import io.swagger.annotations.*;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;


@RestController
public class IdApiController implements IdApi {

    private static final Logger log = LoggerFactory.getLogger(IdApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    public IdApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @Autowired
    private IMobileSubscriberService mobileSubscriberService;

	public ResponseEntity<Void> deleteMobileNumber(
			@ApiParam(value = "Mobile Subscriber ID to delete.", required = true) @PathVariable("id") Long id) {

		HttpStatus httpStatus = HttpStatus.OK;

		try {
			mobileSubscriberService.deleteMobileSubscriber(id);
			log.info("Sucessfully deleted mobile number with id: " + id);
		} catch (NotFoundException nfe) {
			int httpStatusCode = nfe.getCode();
			httpStatus = HttpStatus.valueOf(httpStatusCode);
			log.error("mobile number could not be found: " + id);
		}

		return new ResponseEntity<Void>(httpStatus);
	}

    public ResponseEntity<MobileSubscriberDto> getMobileNumberById(@ApiParam(value = "Mobile Subscriber ID",required=true) @PathVariable("id") Long id) {
       
    	 HttpStatus httpStatus =  HttpStatus.OK;
    	 MobileSubscriber mobileSubscriber  = null;
    	 MobileSubscriberDto mobileSubscriberDto = null;
        
    	try {
    		mobileSubscriber = mobileSubscriberService.getMobileSubscriber(id);
    		mobileSubscriberDto =  dtoConverter.convertToDto(mobileSubscriber);
			log.info("Sucessfully returned mobile number: " + mobileSubscriber);
    	} 	catch (NotFoundException nfe) {
			int httpStatusCode = nfe.getCode();
			httpStatus = HttpStatus.valueOf(httpStatusCode);
			log.error("mobile number could not be found: " + id);
		}
                
        return new ResponseEntity<MobileSubscriberDto>(mobileSubscriberDto,httpStatus);
    }

	public ResponseEntity<MobileSubscriberDto> updateMobileNumber(
			@ApiParam(value = "Mobile Subscriber ID to update.", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "") @Valid @RequestBody MobileSubscriberDto body) {

		String serviceType = body.getServiceType().toString();

		HttpStatus httpStatus = HttpStatus.OK;
		MobileSubscriber mobileSubscriber = null;
		MobileSubscriberDto mobileSubscriberDto = null;

		try {
			mobileSubscriber = mobileSubscriberService.updateMobileSubscriber(id, serviceType);
			mobileSubscriberDto = dtoConverter.convertToDto(mobileSubscriber);
			log.info("Sucessfully updated mobile number: " + mobileSubscriber);
		} catch (NotFoundException nfe) {
			int httpStatusCode = nfe.getCode();
			httpStatus = HttpStatus.valueOf(httpStatusCode);
			log.error("mobile number could not be found: " + id);
		}

		return new ResponseEntity<MobileSubscriberDto>(mobileSubscriberDto, httpStatus);
	}
}
