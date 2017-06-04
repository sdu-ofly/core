package com.ofly.core.admin.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * Introduction	：登录帐号管理
 *
 * Author		：Logan                
 * Create Date	：2017年6月3日 上午8:57:22
 *
 */
@Controller
@RequestMapping("/admin/account")
public class AccountController {
	private static final String PATH_INIT = "core/admin/account/account";
	private static final String PATH_ADD_ACCOUNT = "core/admin/account/addAccount";
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IRoleService roleService;
	
	/**
	 * Introduction	：
	 *
	 * Author		：Logan                
	 * Create Date	：2017年6月3日 上午8:58:00
	 * History		: 2017年6月3日 上午8:58:00   Logan   Created.
	 *
	 * @return
	 *
	 */
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	@RequestMapping("/addAccount")
	public String addAccount() {
		return PATH_ADD_ACCOUNT;
	}
	
	
	
	
	
	/**
	 * Introduction	：修改帐号信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午9:10:09
	 * History		: 2017年6月3日 上午9:10:09   Logan715   Created.
	 * 
	 * @param m		: Model
	 * @param id	: 帐号Id
	 * @return		: 路径
	 *
	 */
	@RequestMapping("/editAccount")
	public String addAccount(Model m,String id) {
		AccountVo account = accountService.queryAccountByPrimaryKey(id);
		m.addAttribute("account", account);
		return PATH_ADD_ACCOUNT;
	}
	
	/**
	 * Introduction	：删除帐号
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午9:11:17
	 * History		: 2017年6月3日 上午9:11:17   Logan715   Created.
	 * 
	 * @param id	: 帐号Id
	 * @return		: 成功失败信息
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/deleteAccount")
	@ResponseBody
	public Map<String, Object> deleteAccount(String id) {
		Map<String, Object> result  =accountService.deleteAccount(id);
		return result;
	}
	
	/**
	 * Introduction	：查询帐号列表信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午9:13:34
	 * History		: 2017年6月3日 上午9:13:34   Logan715   Created.
	 * 
	 * @param condition	: 查询条件[account|name(帐号|用户名称)]
	 * @param page		: 第几页
	 * @param rows		: 显示的行数
	 * @return			: 帐号信息List的json格式数据 
	 *
	 */
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
	/**
	 * Introduction	：保存帐号信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午9:15:55
	 * History		: 2017年6月3日 上午9:15:55   Logan715   Created.
	 * 
	 * @param vo	：帐号Vo
	 * @return		: 成功失败信息
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/saveAccount")
	@ResponseBody
	public Map<String, Object> saveAccount(AccountVo vo) {
		Map<String, Object> result =accountService.saveAccount(vo);
		return result;
	}
	
	/**
	 * Introduction	：查询帐号管理的角色信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午9:17:40
	 * History		: 2017年6月3日 上午9:17:40   Logan715   Created.
	 * 
	 * @param accountId	: 帐号Id	
	 * @param condition	: [角色名称|角色编码]
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return		: 
	 * {
	 * 		total	: 总数量
	 * 		rows	: 查询帐号管理的角色信息List
	 * }
	 *
	 */
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
	
	/**
	 * Introduction	：查询帐号未被使用的角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月3日 上午10:04:05
	 * History		: 2017年6月3日 上午10:04:05   Logan715   Created.
	 * 
	 * @param accountId	: 帐号Id
	 * @param condition	: 查询条件
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return		: 
	 * {
	 * 		total	: 总数量
	 * 		rows	: 查询帐号未被使用的角色List
	 * }
	 *
	 */
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
	
	/**
	 * Introduction	：删除帐号关联角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:42:27
	 * History		: 2017年6月4日 上午9:42:27   Logan715   Created.
	 * 
	 * @param ids	: 关联Id
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/deleteRelaRole")
	@ResponseBody
	public Map<String, Object> deleteRelaRole(String ids) {
		List<String> list = JSONObject.parseArray(ids, String.class);
		Map<String, Object> result = roleService.deleteRelaRole(list);
		return result;
	}
	
	/**
	 * Introduction	：新增管理角色信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:43:29
	 * History		: 2017年6月4日 上午9:43:29   Logan715   Created.
	 * 
	 * @param data	: 新增管理角色信息
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/addReleRole")
	@ResponseBody
	public Map<String, Object> addReleRole(String data) {
		@SuppressWarnings("rawtypes")
		List<Map> list = JSONObject.parseArray(data, Map.class);
		Map<String, Object> result = roleService.addReleRole(list);
		return result;
		
	}
	
	

}
