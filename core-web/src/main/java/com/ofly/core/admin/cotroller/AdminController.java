package com.ofly.core.admin.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String PATH_ADMIN 		= "core/admin/init";
	private static final String PATH_RESOURCES 	= "core/admin/resources";
	private static final String PATH_ROLES 		= "core/admin/roles";
	private static final String PATH_ACCOUNT 	= "core/admin/account";
	@RequestMapping("/init")
	public String init() {
		return PATH_ADMIN;
	}
	@RequestMapping("/initResources")
	public String initResources() {
		return PATH_RESOURCES;
	}
	@RequestMapping("/initRoles")
	public String initRoles() {
		return PATH_ROLES;
	}
	@RequestMapping("/initAccount")
	public String initAccount() {
		return PATH_ACCOUNT;
	}
	
	
}
