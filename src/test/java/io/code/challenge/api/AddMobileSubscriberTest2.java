package io.code.challenge.api;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import io.code.challenge.dao.MobileSubscriberRepository;
import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.service.MobileSubscriberServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class AddMobileSubscriberTest2 {
	
	
    @TestConfiguration
    static class MobileSubscriberServiceImplTestContextConfiguration {
  
        @Bean(name="mobileSubscriberServiceTest")
        public MobileSubscriberServiceImpl mobileSubscriberService() {
            return new MobileSubscriberServiceImpl();
        }
    }	
	
	@MockBean
	private MobileSubscriberRepository mobileSubscriberRepository;


    @Autowired
    @Qualifier("mobileSubscriberServiceTest")
    private MobileSubscriberServiceImpl mobileSubscriberService;


    
    
    
    @Test
    public void addMobileSubscriberCheckIfSaved() throws Exception {    	
    	
    	MobileSubscriber mobileSubscriber = new MobileSubscriber(
    			12341234L,
    			"35699888999",
    			12341234L,
    			12341234L,
    			"MOBILE_PREPAID",
    			1234L);
    	
    	List<MobileSubscriber> mobileSubscribers = new ArrayList<MobileSubscriber>();
    	mobileSubscribers.add(mobileSubscriber);
    	
    	given(mobileSubscriberRepository.findAll()).willReturn(mobileSubscribers);   	
    	mobileSubscriberService.saveMobileSubscriber(mobileSubscriber);
    	List<MobileSubscriber> mlist = mobileSubscriberService.retrieveMobileSubscribers();
    	assertEquals(mlist.size(),1);
    }
    
}
