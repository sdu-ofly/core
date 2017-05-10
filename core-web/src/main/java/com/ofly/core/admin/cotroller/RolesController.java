package com.ofly.core.admin.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("roles")
public class RolesController {
	private static final String PATH_INIT = "core/admib/roles";
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
}
