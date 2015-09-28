package br.com.ulbra.tcc.services.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * The CommonPropertyConfig Class
 * 
 * @author Paulo Pacheco
 *
 */
@Configuration
@PropertySource("classpath:properties/dataquality.properties")
@Component
public class CommonPropertyConfig {

	private static final Logger LOGGER = Logger.getLogger(CommonPropertyConfig.class); 
	
	@Autowired
	private transient Environment environment;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
    public String getProperty(String propertyName){
    	String property = null;
    	try{
    		property = environment.getRequiredProperty(propertyName);
    	} catch(IllegalStateException ise){
    		LOGGER.error("Unable to read property [ " + propertyName + "].");
    	}
    	return property;
    }
}
