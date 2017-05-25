package com.ofly.core.admin.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String PATH_ADMIN 		= "core/admin/init";
	@RequestMapping("/init")
	public String init() {
		return PATH_ADMIN;
	}
	
	
}
