package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IAdminService;
import com.ofly.core.auth.AuthenManager;


/**
 * Introduction	：后台管理Service
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:40:47
 *
 */
@Service
public class AdminService implements IAdminService {

	/**
	 * Introduction	：刷新资源权限
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:41:40
	 * History		: 2017年6月4日 上午10:41:40   Logan715   Created.
	 * 
	 * @return		:
	 * {
	 * 		code	:成功失败标识[成功:1;失败:0]
	 * 		msg 	: 提示信息
	 * }
	 * @throws Exception		
	 *
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
