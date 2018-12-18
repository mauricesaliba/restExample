package io.code.challenge.model;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.code.challenge.entities.MobileSubscriber;
import io.code.challenge.model.MobileSubscriberDto.ServiceTypeEnum;

@Component
public class DtoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public MobileSubscriberDto convertToDto(MobileSubscriber mobileSubscriber) {
		MobileSubscriberDto mobileSubscriberDto = modelMapper.map(mobileSubscriber, MobileSubscriberDto.class);		
		mobileSubscriberDto.setServiceType(ServiceTypeEnum.valueOf(mobileSubscriber.getServiceType().replace("MOBILE_", "")));
		return mobileSubscriberDto;
	}

	public MobileSubscriber convertToEntity(MobileSubscriberDto mobileSubscriberDto) throws ParseException {
		MobileSubscriber mobileSubscriber = modelMapper.map(mobileSubscriberDto, MobileSubscriber.class);
		return mobileSubscriber;
	}
}