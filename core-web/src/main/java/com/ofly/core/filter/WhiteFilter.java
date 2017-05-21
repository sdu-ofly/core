package com.ofly.core.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

public class WhiteFilter extends AccessControlFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest h = (HttpServletRequest)request;
		String requestURI = h.getRequestURI();
		StringBuffer url = h.getRequestURL();
		Subject subject = getSubject(request, response);
		boolean authenticated = subject.isAuthenticated();
		org.apache.shiro.web.filter.authc.AnonymousFilter a;
		String name2 = getName();
		
		System.out.println("WhiteFilter：isAccessAllowed="+false);
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WhiteFilter：onAccessDenied="+true);
		return true;
	}

}
