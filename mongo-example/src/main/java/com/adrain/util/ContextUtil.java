package com.adrain.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author AdrainHuang
 * @Date 2019/8/4 00:26
 **/
public class ContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static <T>  T getBean(Class<T> claz) {
		Object bean = context.getBean(claz.getClass());
		return (T) bean;
	}
}
