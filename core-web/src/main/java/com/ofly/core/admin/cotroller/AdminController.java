package com.ofly.core.admin.cotroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofly.core.admin.api.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String PATH_ADMIN 		= "core/admin/init";
	@Autowired
	private IAdminService adminService;
	@RequestMapping("/init")
	public String init() {
		return PATH_ADMIN;
	}
	/**
	 * @return
	 * 刷新权限角色资源
	 */
	@RequestMapping("/refresh")
	@ResponseBody
	public Map<String, Object> refresh() throws Exception {
		Map<String, Object> map = adminService.refresh();
		return map;
	}
	
}
