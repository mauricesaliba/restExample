package io.code.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.MobileSubscriberAlreadyExistsException;
import io.code.challenge.exceptions.MobileSubscriberNotFoundException;
import io.code.challenge.dao.MobileSubscriberRepository;

@Service("mobileSubscriberService")
@Primary
public class MobileSubscriberServiceImpl implements IMobileSubscriberService {
	
	
	 @Autowired
	 private MobileSubscriberRepository MobileSubscriberRepository;

	@Override
	public List<MobileSubscriber> retrieveMobileSubscribers() {
		List<MobileSubscriber> mobileSubscribers = MobileSubscriberRepository.findAll();
		return mobileSubscribers;
	}

	@Override
	public MobileSubscriber getMobileSubscriber(Long mobileSubscriberId) throws MobileSubscriberNotFoundException {
		Optional<MobileSubscriber> mobileSubscriber = MobileSubscriberRepository.findById(mobileSubscriberId);
		if(!mobileSubscriber.isPresent()) {
			
			throw new MobileSubscriberNotFoundException();
		}
		
		return mobileSubscriber.get();
	}

	@Override
	public void saveMobileSubscriber(MobileSubscriber mobileSubscriber) throws MobileSubscriberAlreadyExistsException {
		if(MobileSubscriberRepository.existsById(mobileSubscriber.getId()))	{
			
			throw new MobileSubscriberAlreadyExistsException();
		}
		
		MobileSubscriberRepository.saveAndFlush(mobileSubscriber);
	}

	@Override
	public void deleteMobileSubscriber(Long mobileSubscriberId) throws MobileSubscriberNotFoundException {
		try {
			MobileSubscriberRepository.deleteById(mobileSubscriberId);
		} catch (EmptyResultDataAccessException erdae) {
			throw new MobileSubscriberNotFoundException();
		}
	}

	@Transactional
	@Override	
	public MobileSubscriber updateMobileSubscriber(Long id, String serviceType) throws MobileSubscriberNotFoundException {
		
        MobileSubscriber mobileSubscriber = getMobileSubscriber(id);        
        mobileSubscriber.setServiceType(serviceType);
        MobileSubscriberRepository.saveAndFlush(mobileSubscriber);
        return mobileSubscriber;
	}
	
	
	@Override
	public void deleteAll() {		

        MobileSubscriberRepository.deleteAll();
  	}
}

