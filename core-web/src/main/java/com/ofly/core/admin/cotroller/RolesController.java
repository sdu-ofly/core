package com.ofly.core.admin.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Logan
 * 角色管理界面
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.api.IRoleService;
import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.RoleResRelaVo;
import com.ofly.core.admin.vo.RoleVo;
/**
 * Introduction	：角色管理
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:03:57
 *
 */
@Controller
@RequestMapping("/admin/roles")
public class RolesController {
	private static final String PATH_INIT = "core/admin/role/roles";
	private static final String PATH_ADD_ROLE = "core/admin/role/addRole";
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IResourcesService resourcesService;
	/**
	 * Introduction	：初始化角色管理界面
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:04:21
	 * History		: 2017年6月4日 上午10:04:21   Logan715   Created.
	 * 
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	
	
	/**
	 * Introduction	：新增角色界面
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:04:37
	 * History		: 2017年6月4日 上午10:04:37   Logan715   Created.
	 * 
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/addRole")
	public String addRole() {
		return PATH_ADD_ROLE;
	}
	
	/**
	 * Introduction	：初始化编辑角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:04:50
	 * History		: 2017年6月4日 上午10:04:50   Logan715   Created.
	 * 
	 * @param m		: Model
	 * @param id	: 角色Id
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/editRole")
	public String editRole(Model m, String id) {
		RoleVo vo = roleService.queryRoleByPrimarykey(id);
		m.addAttribute("role", vo);
		return PATH_ADD_ROLE;
	}
	
	/**
	 * Introduction	：查询角色List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:05:38
	 * History		: 2017年6月4日 上午10:05:38   Logan715   Created.
	 * 
	 * @param condition	: (角色名称|角色编码)可模糊查询
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return			: 角色List的Json数据
	 *
	 */
	@RequestMapping("/queryRoleList")
	@ResponseBody
	public JSONObject queryRoleList(String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("condition",condition);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<RoleVo> list = roleService.queryRoleList(params);
		int num = roleService.queryRoleListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * Introduction	：保存对应的角色数据
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:09:15
	 * History		: 2017年6月4日 上午10:09:15   Logan715   Created.
	 * 
	 * @param vo	: 角色数据
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public Map<String, Object> saveRole(RoleVo vo) {
		Map<String, Object> result = roleService.saveRole(vo);
		return result;
	}
	
	/**
	 * Introduction	：删除角色信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:09:56
	 * History		: 2017年6月4日 上午10:09:56   Logan715   Created.
	 * 
	 * @param id	: 角色Id
	 * @return		:
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(String id) {
		Map<String, Object> result = roleService.deleteRoleByPrimaryKey(id);
		return result;
	}
	
	/**
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 * 
	 */
	/**
	 * Introduction	：查询角色管理的资源信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:10:41
	 * History		: 2017年6月4日 上午10:10:41   Logan715   Created.
	 * 
	 * @param roleId	: 角色Id
	 * @param condition	: (资源名称|资源路径)可模糊查询
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return			: Json数据 
	 * {
	 * 		total	: 总数
	 * 		rows	: 查询角色管理的资源信息List
	 * }
	 *
	 */
	@RequestMapping("/queryResoueceWithRole")
	@ResponseBody JSONObject queryResoueceWithRole(String roleId, String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("condition",condition);
		params.put("roleId",roleId);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<RoleResRelaVo> list = resourcesService.queryExistResourceList(params);
		int num = resourcesService.queryExistResourceListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	/**
	 * Introduction	：删除角色管理的资源
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:13:18
	 * History		: 2017年6月4日 上午10:13:18   Logan715   Created.
	 * 
	 * @param ids	: 关联资源Id数组的Json字符串
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/deleteRelaResource")
	@ResponseBody
	public Map<String, Object> deleteRelaResource(String ids) {
		List<String> arr = JSONObject.parseArray(ids, String.class);
		Map<String, Object> result = resourcesService.deleteRelaResource(arr);
		return result;
	}
	/**
	 * Introduction	：查询角色未关联的资源信息List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:14:46
	 * History		: 2017年6月4日 上午10:14:46   Logan715   Created.
	 * 
	 * @param roleId	: 角色Id
	 * @param parentId	: 父节点Id
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return			: Json数据
	 * {
	 * 		total	: 总数
	 * 		rows	: 查询角色未关联的资源信息List
	 * }
	 *
	 */
	@RequestMapping("/queryUnUseResourceList")
	@ResponseBody
	public JSONObject queryUnUseResourceList(String roleId,String parentId
										,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("roleId",roleId);
		params.put("parentId",parentId);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<ResourceVo> list = resourcesService.queryUnUseResourceList(params);
		int num = resourcesService.queryUnUseResourceListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	/**
	 * Introduction	：新增角色管理资源
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:16:40
	 * History		: 2017年6月4日 上午10:16:40   Logan715   Created.
	 * 
	 * @param data	: 关联资源数据数组的json字符串
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/addRelaResource")
	@ResponseBody
	public Map<String, Object> addRelaResource(String data) {
		@SuppressWarnings("rawtypes")
		List<Map> arr = JSONObject.parseArray(data,Map.class);
		Map<String, Object> result = resourcesService.addRelaResource(arr);
		return result;
	}
}
