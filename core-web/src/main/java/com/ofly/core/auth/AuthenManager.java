package com.ofly.core.auth;

public class AuthenManager {
	public String loadFilterChainDefinitions() {
		return getFilterChainDefinitions();
	}
	
	private String getFilterChainDefinitions() {
		StringBuffer sb = new StringBuffer();
		sb.append("/login/*=roles");
		
		return sb.toString();
	}

}
