package com.ofly.core.admin.cotroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofly.core.admin.api.IAdminService;

/**
 * Introduction	：后台管理
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午9:54:55
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String PATH_ADMIN 		= "core/admin/init";
	@Autowired
	private IAdminService adminService;
	/**
	 * Introduction	：初始化后台管理界面
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:55:02
	 * History		: 2017年6月4日 上午9:55:02   Logan715   Created.
	 * 
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/init")
	public String init() {
		return PATH_ADMIN;
	}
	
	/**
	 * Introduction	：刷新资源权限信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:54:58
	 * History		: 2017年6月4日 上午9:54:58   Logan715   Created.
	 * 
	 * @return
	 * @throws Exception		: 
	 *
	 */
	@RequestMapping("/refresh")
	@ResponseBody
	public Map<String, Object> refresh() throws Exception {
		Map<String, Object> map = adminService.refresh();
		return map;
	}
	
}
