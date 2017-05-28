package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IAdminService;
import com.ofly.core.auth.AuthenManager;

/**
 * @author Logan
 *
 */
@Service
public class AdminService implements IAdminService {

	/* (non-Javadoc)
	 * @see com.ofly.core.admin.api.IAdminService#refresh()
	 */
	@Override
	public Map<String, Object> refresh() throws Exception {
		Map<String, Object> result = new HashMap<>(); 
		boolean flag = AuthenManager.getInstance().reloadFilterChainDefinitions();
		String code = flag?"1":"0";
		String msg = flag?"刷新成功":"有人在刷新,请稍后直接访问你的权限角色";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

}
