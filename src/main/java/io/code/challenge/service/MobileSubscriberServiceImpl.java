package io.code.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.exceptions.AlreadyExistsException;
import io.code.challenge.exceptions.NotFoundException;
import io.code.challenge.dao.MobileSubscriberRepository;

@Service
public class MobileSubscriberServiceImpl implements IMobileSubscriberService {
	
	
	 @Autowired
	 private MobileSubscriberRepository MobileSubscriberRepository;

	@Override
	public List<MobileSubscriber> retrieveMobileSubscribers() {
		List<MobileSubscriber> mobileSubscribers = MobileSubscriberRepository.findAll();
		return mobileSubscribers;
	}

	@Override
	public MobileSubscriber getMobileSubscriber(Long mobileSubscriberId) throws NotFoundException {
		Optional<MobileSubscriber> mobileSubscriber = MobileSubscriberRepository.findById(mobileSubscriberId);
		if(!mobileSubscriber.isPresent()) {
			
			throw new NotFoundException();
		}
		
		return mobileSubscriber.get();
	}

	@Override
	public void saveMobileSubscriber(MobileSubscriber mobileSubscriber) throws AlreadyExistsException {
		if(MobileSubscriberRepository.existsById(mobileSubscriber.getId()))	{
			
			throw new AlreadyExistsException();
		}
		
		MobileSubscriberRepository.saveAndFlush(mobileSubscriber);
	}

	@Override
	public void deleteMobileSubscriber(Long mobileSubscriberId) throws NotFoundException {
		try {
			MobileSubscriberRepository.deleteById(mobileSubscriberId);
		} catch (EmptyResultDataAccessException erdae) {
			throw new NotFoundException();
		}
	}

	@Transactional
	@Override	
	public MobileSubscriber updateMobileSubscriber(Long id, String serviceType) throws NotFoundException {
		
        MobileSubscriber mobileSubscriber = getMobileSubscriber(id);        
        mobileSubscriber.setServiceType(serviceType);
        MobileSubscriberRepository.saveAndFlush(mobileSubscriber);
        return mobileSubscriber;
	}
}

