package io.swagger.api;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.code.challenge.api.DefaultApi;
import io.code.challenge.model.MobileSubscriberDto;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultApiControllerIntegrationTest {

    @Autowired
    private DefaultApi api;

    @Test
    public void addMobileNumberTest() throws Exception {
        MobileSubscriberDto body = new MobileSubscriberDto();
        ResponseEntity<MobileSubscriberDto> responseEntity = api.addMobileNumber(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getMobileNumbersTest() throws Exception {
        String msisdn = "msisdn_example";
        Long customerIdOwner = 789L;
        Long customerIdUser = 789L;
        String serviceType = "serviceType_example";
        ResponseEntity<List<MobileSubscriberDto>> responseEntity = api.getMobileNumbers(msisdn, customerIdOwner, customerIdUser, serviceType);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
