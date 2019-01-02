package io.code.challenge.api;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import io.code.challenge.dao.MobileSubscriberRepository;
import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberAlreadyExistsException;
import io.code.challenge.service.MobileSubscriberServiceImpl;


import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
public class AddMobileSubscriberTest {
	
	
	@Mock
	private MobileSubscriberRepository mobileSubscriberRepository;


    @InjectMocks
    private MobileSubscriberServiceImpl mobileSubscriberService;

    @Test(expected=MobileSubscriberAlreadyExistsException.class)
    public void addMobileSubscriberWithTakenId() throws Exception {    	
    	
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
    
    
    
   /* @Test
    public void addMobileSubscriberCheckIfSaved() throws Exception {    	
    	
    	MobileSubscriber mobileSubscriber = new MobileSubscriber(
    			12341234L,
    			"35699888999",
    			12341234L,
    			12341234L,
    			"MOBILE_PREPAID",
    			1234L);
    	
    	mobileSubscriberRepository.deleteAll();    	
    	mobileSubscriberService.saveMobileSubscriber(mobileSubscriber);
    	List<MobileSubscriber> mlist = mobileSubscriberService.retrieveMobileSubscribers();
    	System.out.println(mlist);
    	assertEquals(1, 2);
    }
    
    */
    
   
    
    

    
    
    
}
