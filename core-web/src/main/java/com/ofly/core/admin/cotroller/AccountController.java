package com.ofly.core.admin.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
	private static final String PATH_INIT = "core/admin/account/account";
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}

}
