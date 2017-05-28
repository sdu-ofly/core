package com.ofly.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext   applicationContext;
	
	@Override
	//实现ApplicationContextAware接口的回调方法，设置上下文环境
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
	
	public static Object getBean(String name) throws BeansException{

        return applicationContext.getBean(name);
    }

	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	public static HttpServletResponse getResponse(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
}
