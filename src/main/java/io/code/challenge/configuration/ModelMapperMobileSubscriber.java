package io.code.challenge.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperMobileSubscriber {
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
