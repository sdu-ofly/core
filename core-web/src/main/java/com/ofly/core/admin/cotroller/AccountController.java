package com.ofly.core.admin.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ofly.core.admin.api.IAccountService;
import com.ofly.core.admin.api.IRoleService;
import com.ofly.core.admin.vo.AccountRoleRelaVo;
import com.ofly.core.admin.vo.AccountVo;
import com.ofly.core.admin.vo.RoleVo;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
	private static final String PATH_INIT = "core/admin/account/account";
	private static final String PATH_ADD_ACCOUNT = "core/admin/account/addAccount";
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IRoleService roleService;
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	@RequestMapping("/addAccount")
	public String addAccount() {
		return PATH_ADD_ACCOUNT;
	}
	@RequestMapping("/editAccount")
	public String addAccount(Model m,String id) {
		AccountVo account = accountService.queryAccountByPrimaryKey(id);
		m.addAttribute("account", account);
		return PATH_ADD_ACCOUNT;
	}
	@RequestMapping("/deleteAccount")
	@ResponseBody
	public Map<String, Object> deleteAccount(String id) {
		Map<String, Object> result  =accountService.deleteAccount(id);
		return result;
	}
	
	@RequestMapping("/queryAccountList")
	@ResponseBody
	public JSONObject queryAccountList(String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("condition",condition);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<AccountVo> list = accountService.queryAccountList(params);
		int num = accountService.queryAccountListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	@RequestMapping("/saveAccount")
	@ResponseBody
	public Map<String, Object> saveAccount(AccountVo vo) {
		Map<String, Object> result =accountService.saveAccount(vo);
		return result;
	}
	@RequestMapping("/queryRelaRoleList")
	@ResponseBody
	public JSONObject queryRelaRoleList(String accountId, String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("accountId",accountId);
		params.put("condition",condition);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<AccountRoleRelaVo> list = roleService.queryRelaRoleList(params);
		int num = roleService.queryRelaRoleListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping("/queryUnUseRoleList")
	@ResponseBody
	public JSONObject queryUnUseRoleList(String accountId,String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("accountId",accountId);
		params.put("condition",condition);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<RoleVo> list = roleService.queryUnUseRoleList(params);
		int num = roleService.queryUnUseRoleListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	
	

}
