package io.code.challenge.api;

import io.code.challenge.exceptions.ApiException;
import io.code.challenge.model.MobileSubscriberDto;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value = "default")
public interface DefaultApi {

    @ApiOperation(value = "Add a mobile number", nickname = "addMobileNumber", notes = "Add a mobile number.", response = MobileSubscriberDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Operation.", response = MobileSubscriberDto.class),
        @ApiResponse(code = 400, message = "Bad request / failed validation."),
        @ApiResponse(code = 409, message = "Already exists."),
        @ApiResponse(code = 500, message = "Server error.") })
    @RequestMapping(value = "/",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<MobileSubscriberDto> addMobileNumber(@ApiParam(value = ""  )  @Valid @RequestBody MobileSubscriberDto body) throws ApiException;


    @ApiOperation(value = "Get all Mobile Numbers", nickname = "getMobileNumbers", notes = "Get all Mobile Numbers based on a filter. For the purpose of this, excercise the implementation should only cater for the scenario that no parameters are sent within the request.", response = MobileSubscriberDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Operation.", response = MobileSubscriberDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid search criteria value."),
        @ApiResponse(code = 500, message = "Server error.") })
    @RequestMapping(value = "/",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<MobileSubscriberDto>> getMobileNumbers(@ApiParam(value = "The mobile number in E164 format.") @Valid @RequestParam(value = "msisdn", required = false) String msisdn,@ApiParam(value = "The ID referencing the owner of the mobile numbers to be searched.") @Valid @RequestParam(value = "customer_id_owner", required = false) Long customerIdOwner,@ApiParam(value = "The ID referencing the user of the mobile numbers to be searched.") @Valid @RequestParam(value = "customer_id_user", required = false) Long customerIdUser,@ApiParam(value = "Type of mobile numbers to be searched.", allowableValues = "MOBILE_PREPAID, MOBILE_POSTPAID") @Valid @RequestParam(value = "service_type", required = false) String serviceType);

}
