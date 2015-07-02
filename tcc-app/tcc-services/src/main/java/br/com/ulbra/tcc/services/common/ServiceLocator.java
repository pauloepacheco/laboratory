package br.com.ulbra.tcc.services.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Service Locator Class
 * 
 * @author Eduardo
 *
 */
public class ServiceLocator {

	private static ApplicationContext APPLICATION_CONTEXT;
	
	private static final String SERVICES_CONFIG = "tcc-services.xml";
	
	public static <T> T getServiceInstance(String beanName, Class<T> clazz) {
		Object bean =  getInstance().getBean(beanName);
		if(null == bean){
			/*LOGGER.error("unable to locate service instance for the bean with name: " + beanName);
			throw new EvolutionTechnicalException();*/
		}
		
		try{
			return clazz.cast(bean);
		}catch(ClassCastException ex){
		/*	LOGGER.error("wrong  combination of bean name and class type passed, bean name: " + beanName + " class:" + clazz.getName());
			throw new EvolutionTechnicalException();*/
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
