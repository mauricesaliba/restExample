package io.code.challenge.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.AlreadyExistsException;
import io.code.challenge.exceptions.NotFoundException;


@Service
public interface IMobileSubscriberService {

	 public List<MobileSubscriber> retrieveMobileSubscribers();
	 
	 public MobileSubscriber getMobileSubscriber(Long mobileSubscriberId) throws NotFoundException;
	 
	 public void saveMobileSubscriber(MobileSubscriber mobileSubscriber) throws AlreadyExistsException;
	 
	 public void deleteMobileSubscriber(Long mobileSubscriberId) throws NotFoundException;
	 
	 
	 public MobileSubscriber updateMobileSubscriber(Long mobileSubscriberId, String serviceType) throws NotFoundException;
}









