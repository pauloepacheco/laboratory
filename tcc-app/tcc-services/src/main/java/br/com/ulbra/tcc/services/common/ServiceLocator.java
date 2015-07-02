package br.com.ulbra.tcc.services.common;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Service Locator Class
 * 
 * @author Paulo Pacheco
 *
 */
public class ServiceLocator {

	private static ApplicationContext APPLICATION_CONTEXT;
	
	private static final String SERVICES_CONFIG = "tcc-services.xml";
	
	private static final Logger LOGGER = Logger.getLogger(ServiceLocator.class);
	
	public static <T> T getServiceInstance(String beanName, Class<T> clazz) {
		Object bean =  getInstance().getBean(beanName);
		if(null == bean){
			LOGGER.error("unable to locate service instance for the bean with name: " + beanName);
			//TODO: throw Service Exception
		}
		
		try{
			return clazz.cast(bean);
		}catch(ClassCastException ex){
			LOGGER.error("wrong  combination of bean name and class type passed, bean name: " + beanName + " class:" + clazz.getName());
			//TODO: throw Service Exception
			return null;
		}		
	}
	
	private static ApplicationContext getInstance(){
		if(APPLICATION_CONTEXT == null){
			APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
					SERVICES_CONFIG);
		}
		
		return APPLICATION_CONTEXT;
	}
	
}
