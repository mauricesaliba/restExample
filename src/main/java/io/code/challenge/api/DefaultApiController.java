package io.code.challenge.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.AlreadyExistsException;
import io.code.challenge.model.DtoConverter;
import io.code.challenge.model.MobileSubscriberDto;
import io.code.challenge.service.IMobileSubscriberService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DefaultApiController implements DefaultApi {

    private static final Logger log = LoggerFactory.getLogger(DefaultApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private IMobileSubscriberService mobileSubscriberService;
    
    @Autowired
    private DtoConverter dtoConverter;

    @org.springframework.beans.factory.annotation.Autowired
    public DefaultApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<MobileSubscriberDto> addMobileNumber(@ApiParam(value = ""  )  @Valid @RequestBody MobileSubscriberDto body) {
        
        MobileSubscriber mobileSubscriber  = null;
        HttpStatus httpStatus =  HttpStatus.CREATED;
        
        try {
        	mobileSubscriber = dtoConverter.convertToEntity(body);
			mobileSubscriberService.saveMobileSubscriber(mobileSubscriber);
			log.info("Sucessfully created: " + mobileSubscriber);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyExistsException aee) {
			int httpStatusCode = aee.getCode();
			httpStatus = HttpStatus.valueOf(httpStatusCode);
			log.error("mobile subscriber already created exception on mobile subscriber with id: " + mobileSubscriber.getId());
		}
        
        MobileSubscriberDto mobileSubscriberDto = dtoConverter.convertToDto(mobileSubscriber);       
        return new ResponseEntity<MobileSubscriberDto>(mobileSubscriberDto,httpStatus);
    }

    public ResponseEntity<List<MobileSubscriberDto>> getMobileNumbers(@ApiParam(value = "The mobile number in E164 format.") @Valid @RequestParam(value = "msisdn", required = false) String msisdn,@ApiParam(value = "The ID referencing the owner of the mobile numbers to be searched.") @Valid @RequestParam(value = "customer_id_owner", required = false) Long customerIdOwner,@ApiParam(value = "The ID referencing the user of the mobile numbers to be searched.") @Valid @RequestParam(value = "customer_id_user", required = false) Long customerIdUser,@ApiParam(value = "Type of mobile numbers to be searched.", allowableValues = "MOBILE_PREPAID, MOBILE_POSTPAID") @Valid @RequestParam(value = "service_type", required = false) String serviceType) {
        
        List<MobileSubscriberDto> mobileSubscribersDtos = new ArrayList<MobileSubscriberDto>();                     
        List<MobileSubscriber> mobileSubscribers = mobileSubscriberService.retrieveMobileSubscribers();
        mobileSubscribers.forEach((v)-> mobileSubscribersDtos.add(dtoConverter.convertToDto(v)) );
        
        log.info("Sucessfully returned list of mobile subscribers [count: " + mobileSubscribersDtos.size() + "]");        
        return new ResponseEntity<List<MobileSubscriberDto>>(mobileSubscribersDtos,HttpStatus.OK);
    }

}
