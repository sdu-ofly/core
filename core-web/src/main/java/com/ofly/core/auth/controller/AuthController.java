package com.ofly.core.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
	private static final String PATH_403 = "core/auth/403";
	@RequestMapping("/403")
	public String unauthorized() {
		return PATH_403;
	}
	
}
