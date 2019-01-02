package io.code.challenge.api;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import io.code.challenge.dao.MobileSubscriberRepository;
import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberAlreadyExistsException;
import io.code.challenge.exceptions.MobileSubscriberNotFoundException;
import io.code.challenge.service.MobileSubscriberServiceImpl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class AddNewMobileSubscriberTesting {
	
	
	@Mock
	private MobileSubscriberRepository mobileSubscriberRepository;


    @InjectMocks
    private MobileSubscriberServiceImpl mobileSubscriberService;

    @Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test(expected=MobileSubscriberAlreadyExistsException.class)
    public void AddingMobileSubscriberWithTakenIdThrowsException() throws Exception {    	
    	
    	MobileSubscriber mobileSubscriber = new MobileSubscriber(
    			12341234L,
    			"35699888999",
    			12341234L,
    			12341234L,
    			"MOBILE_PREPAID",
    			1234L);
    	
    	
    	when(mobileSubscriberRepository.existsById(anyLong())).thenReturn(true);    	
    	mobileSubscriberService.saveMobileSubscriber(mobileSubscriber);
    }
    
    
    
    @Test(expected=MobileSubscriberNotFoundException.class)
    public void DeletingNonExistentSubscriberThrowsException() throws Exception {    	
    	
    	doThrow(new EmptyResultDataAccessException(0)).when(mobileSubscriberRepository).deleteById(anyLong());    	
    	mobileSubscriberService.deleteMobileSubscriber(anyLong());
    }
}
