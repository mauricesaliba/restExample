package io.code.challenge.api;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.code.challenge.dao.MobileSubscriberRepository;
import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberAlreadyExistsException;
import io.code.challenge.exceptions.MobileSubscriberNotFoundException;
import io.code.challenge.service.IMobileSubscriberService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@WebMvcTest(IdApiController.class)
public class IntegrationTestingWebLayer {

    @Autowired
    private MockMvc mockMvc;
    
	@MockBean
	private MobileSubscriberRepository mobileSubscriberRepository;

    @MockBean
    private IMobileSubscriberService mobileSubscriberService;
    
    private final String MOBILE_SUBSCRIBER_REQUEST =    
	    "{\"id\": 23112452813," + 
	        "\"msisdn\": 3561234912," +
	        "\"customer_id_owner\": 123456," +
	        "\"customer_id_user\": 123456," +
	        "\"service_type\": \"MOBILE_PREPAID\"," +
	        "\"service_start_date\": 1528208058557}";
    
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
 
    @Test
    public void CannotDeleteMobileNonExistentSubscriber() throws Exception {    	

    	  doThrow(new MobileSubscriberNotFoundException()).when(mobileSubscriberService).deleteMobileSubscriber(anyLong());
    	  this.mockMvc.perform(MockMvcRequestBuilders
    				.delete("/mobilesubscriber/{id}", anyLong()))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
    				.andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString("Mobile subscriber was not found.")));
    } 
    
    @Test
    public void DeletingExistentSubscriberReturnsSuccessful() throws Exception {    	

    	  doNothing().when(mobileSubscriberService).deleteMobileSubscriber(anyLong());
    	  this.mockMvc.perform(MockMvcRequestBuilders
    				.delete("/mobilesubscriber/{id}", anyLong()))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.NO_CONTENT.value()))
    				.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void AddingSameMobileNumberTwiceReturns404Error() throws Exception {    	
    	
    	doThrow(new MobileSubscriberAlreadyExistsException()).when(mobileSubscriberService).saveMobileSubscriber(any());
    	 this.mockMvc.perform(MockMvcRequestBuilders
    				.post("/mobilesubscriber/")
    				.content(MOBILE_SUBSCRIBER_REQUEST)
					.contentType(APPLICATION_JSON_UTF8))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.CONFLICT.value()))
    				.andExpect(content().string(containsString("Mobile Subscriber Already exists.")));
    }    
    
    @Test
    public void AddMobileNumberWithNoExceptions() throws Exception {    
    	
    	 this.mockMvc.perform(MockMvcRequestBuilders
    				.post("/mobilesubscriber/")
    				.content(MOBILE_SUBSCRIBER_REQUEST)
					.contentType(APPLICATION_JSON_UTF8))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.CREATED.value()));
    }
    
    @Test
    public void FetchingAllMobileNumbersIfNoneExistReturnEmptyArray() throws Exception {    	
    	
    	 this.mockMvc.perform(MockMvcRequestBuilders
 				    .get("/mobilesubscriber/"))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.OK.value()))
    				.andExpect(content().string("[]")); 
    }
    
    @Test
    public void FetchingMobileNumberThatDoesNotExistReturn404Error() throws Exception {    	
    	 
    	 doThrow(new MobileSubscriberNotFoundException()).when(mobileSubscriberService).getMobileSubscriber(anyLong());
    	 this.mockMvc.perform(MockMvcRequestBuilders
			        .get("/mobilesubscriber/{id}", anyLong()))
    				.andDo(print())
    				.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
    				.andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString("Mobile subscriber was not found."))); 
    } 
    
    @Test
    public void FetchingExistentSubscriberReturnsSuccessful() throws Exception {    	

    	MobileSubscriber mobileSubscriber = new MobileSubscriber(
    			12341234L,
    			"35699888999",
    			12345555L,
    			12344444L,
    			"MOBILE_PREPAID",
    			1234L);
    	
    	
    	  doReturn(mobileSubscriber).when(mobileSubscriberService).getMobileSubscriber(anyLong());
    	  this.mockMvc.perform(MockMvcRequestBuilders
    			  	.get("/mobilesubscriber/{id}", anyLong()))
					.andDo(print())
    				.andExpect(status().is(HttpStatus.OK.value()))
    				.andExpect(status().is2xxSuccessful())
    				.andExpect(jsonPath("$.id", is(12341234)))
    				.andExpect(jsonPath("$.msisdn", is("35699888999")))
    				.andExpect(jsonPath("$.customer_id_owner", is(12345555)))
    				.andExpect(jsonPath("$.customer_id_user", is(12344444)))
    	  			.andExpect(jsonPath("$.service_type", is("MOBILE_PREPAID")))
    	  			.andExpect(jsonPath("$.service_start_date", is(1234)));
    }
    
    
    @Test
    public void UpdatingMobileNumberThatDoesNotExistReturn404Error() throws Exception {    	
    	 
    	 doThrow(new MobileSubscriberNotFoundException()).when(mobileSubscriberService).updateMobileSubscriber(anyLong(),any());
    	 this.mockMvc.perform(MockMvcRequestBuilders
			        .put("/mobilesubscriber/{id}", 1234L)
					.content(MOBILE_SUBSCRIBER_REQUEST)
					.contentType(APPLICATION_JSON_UTF8))
			        .andDo(print())
    				.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                    .andExpect(content().string(containsString("Mobile subscriber was not found."))); 
    }
}
