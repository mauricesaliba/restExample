package io.code.challenge.service;

import java.util.List;
import org.springframework.stereotype.Service;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberAlreadyExistsException;
import io.code.challenge.exceptions.MobileSubscriberNotFoundException;


@Service
public interface IMobileSubscriberService {

	 public List<MobileSubscriber> retrieveMobileSubscribers();
	 
	 public MobileSubscriber getMobileSubscriber(Long mobileSubscriberId) throws MobileSubscriberNotFoundException;
	 
	 public void saveMobileSubscriber(MobileSubscriber mobileSubscriber) throws MobileSubscriberAlreadyExistsException;
	 
	 public void deleteMobileSubscriber(Long mobileSubscriberId) throws MobileSubscriberNotFoundException;	 
	 
	 public void deleteAll();
	 
	 public MobileSubscriber updateMobileSubscriber(Long mobileSubscriberId, String serviceType) throws MobileSubscriberNotFoundException;
}









