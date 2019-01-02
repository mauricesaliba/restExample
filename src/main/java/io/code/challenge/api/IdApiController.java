package io.code.challenge.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberNotFoundException;
import io.code.challenge.model.DtoConverter;
import io.code.challenge.model.MobileSubscriberDto;
import io.code.challenge.service.IMobileSubscriberService;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    
    @Autowired(required=false)
    private IMobileSubscriberService mobileSubscriberService;

	public ResponseEntity<Void> deleteMobileNumber  (
			@ApiParam(value = "Mobile Subscriber ID to delete.", required = true) @PathVariable("id") Long id) throws MobileSubscriberNotFoundException {

		mobileSubscriberService.deleteMobileSubscriber(id);
		log.info("Sucessfully deleted mobile number with id: " + id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

    public ResponseEntity<MobileSubscriberDto> getMobileNumberById(@ApiParam(value = "Mobile Subscriber ID",required=true) @PathVariable("id") Long id) throws MobileSubscriberNotFoundException {
       
    	 HttpStatus httpStatus =  HttpStatus.OK;
    	 MobileSubscriber mobileSubscriber  = null;
    	 MobileSubscriberDto mobileSubscriberDto = null;
        
		mobileSubscriber = mobileSubscriberService.getMobileSubscriber(id);
		mobileSubscriberDto =  dtoConverter.convertToDto(mobileSubscriber);
		log.info("Sucessfully returned mobile number: " + mobileSubscriber);

                
        return new ResponseEntity<MobileSubscriberDto>(mobileSubscriberDto,httpStatus);
    }

	public ResponseEntity<MobileSubscriberDto> updateMobileNumber(
			@ApiParam(value = "Mobile Subscriber ID to update.", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "") @Valid @RequestBody MobileSubscriberDto body) throws MobileSubscriberNotFoundException {

		String serviceType = body.getServiceType().toString();

		MobileSubscriber mobileSubscriber = mobileSubscriberService.updateMobileSubscriber(id, serviceType);
		MobileSubscriberDto mobileSubscriberDto = dtoConverter.convertToDto(mobileSubscriber);
		log.info("Sucessfully updated mobile number: " + mobileSubscriber);
		
		return new ResponseEntity<MobileSubscriberDto>(mobileSubscriberDto, HttpStatus.OK);
	}
}
