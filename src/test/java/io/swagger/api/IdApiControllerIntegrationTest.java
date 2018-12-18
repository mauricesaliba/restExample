package io.swagger.api;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.code.challenge.api.IdApi;
import io.code.challenge.model.MobileSubscriberDto;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdApiControllerIntegrationTest {

    @Autowired
    private IdApi api;

    @Test
    public void deleteMobileNumberTest() throws Exception {
        Long id = 789L;
        ResponseEntity<Void> responseEntity = api.deleteMobileNumber(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getMobileNumberByIdTest() throws Exception {
        Long id = 789L;
        ResponseEntity<MobileSubscriberDto> responseEntity = api.getMobileNumberById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateMobileNumberTest() throws Exception {
        Long id = 789L;
        MobileSubscriberDto body = new MobileSubscriberDto();
        ResponseEntity<MobileSubscriberDto> responseEntity = api.updateMobileNumber(id, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
