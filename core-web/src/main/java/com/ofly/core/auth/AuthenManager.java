package com.ofly.core.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.vo.ResourceVo;

public class AuthenManager {
	@Autowired
	private IResourcesService resourcesService;
	private static final String CRLF = "\t\n";
	public String loadFilterChainDefinitions() {
		return getFilterChainDefinitions();
	}
	
	private String getFilterChainDefinitions() {
		StringBuffer sb = new StringBuffer();
		List<ResourceVo> list = resourcesService.queryAllResource();
		if(list!=null && !list.isEmpty()) {
			for (ResourceVo vo : list) {
				if("1".equals(vo.getAuth())) {
					sb.append(vo.getUrl()).append(" = ").append("authc").append(CRLF);
				} else {
					sb.append(vo.getUrl()).append(" = ").append("anon").append(CRLF);
				}
			}
		}
		return sb.toString();
	}

}
