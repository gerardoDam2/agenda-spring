package es.sccode.springdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.sccode.springdemo.component.RequestTime;

@Configuration
public class WebMvcConfiguration  extends WebMvcConfigurerAdapter{

	@Autowired
	@Qualifier("requestTime")
	RequestTime requestTime;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestTime);
	}

	
}
