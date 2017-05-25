package com.ofly.core.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofly.core.admin.vo.AccountVo;

@RequestMapping("/auth/login")
@Controller
public class LoginController {
	private static final String PATH_LOGIN = "core/auth/login";
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@RequestMapping("/init")
	public String init() {
		return PATH_LOGIN;
	}

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, AccountVo vo) {
		Map<String, Object> result = new HashMap<>();
		UsernamePasswordToken token = new UsernamePasswordToken(vo.getAccount(), vo.getPassword());
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			boolean role = subject.hasRole("admin");
			
			result.put("code", 1);
			result.put("msg", "登录成功");
			result.put("url", request.getContextPath()+"/admin/init");
		} catch (IncorrectCredentialsException e) {
			String msg = "登录密码错误. Password for account " + token.getPrincipal() +" was incorrect.";
			logger.error(msg);
			result.put("code", 0);
			result.put("msg", "您输入的帐号或者密码有误");
		} catch (ExcessiveAttemptsException e) {
			String msg = "登录失败次数过多";
			logger.error(msg);
			result.put("code", 0);
			result.put("msg", "您输入的帐号或者密码有误");
		} catch (LockedAccountException e) {
			String msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
			logger.error(msg);
			result.put("code", 0);
			result.put("msg", "帐号已被锁定");
		} catch (DisabledAccountException e) {
			//该系统没有这个
			/* * msg = "帐号已被禁用. The account for username " + token.getPrincipal()
			 * + " was disabled."; model.addAttribute("message", msg);
			 * System.out.println(msg);
			 */
		} catch (ExpiredCredentialsException e) {
			//该系统没有这个
			/*
			 * msg = "帐号已过期. the account for username " + token.getPrincipal() +
			 * "  was expired."; model.addAttribute("message", msg);
			 * System.out.println(msg);
			 */
		} catch (UnknownAccountException e) {
			String msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
			logger.error(msg);
			result.put("code", 0);
			result.put("msg", "您输入的帐号或者密码有误");
			/*
			 * msg = "帐号不存在. There is no user with username of " +
			 * token.getPrincipal(); model.addAttribute("message", msg);
			 * System.out.println(msg);
			 */
		} catch (UnauthorizedException e) {
			// 不再这边做
			/*
			 * msg = "您没有得到相应的授权！" + e.getMessage();
			 * model.addAttribute("message", msg); System.out.println(msg);
			 */
		}
		
		return result;
	}
}
