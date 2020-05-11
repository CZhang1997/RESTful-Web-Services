package com.crz.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Churong Zhang
 * @Date May 11, 2020
 * @Email churongzhang1997@gmail.com
 */
public class SpringApplicationContext implements ApplicationContextAware{

	private static ApplicationContext CONTEXT;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
		
	}
	
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}

}
