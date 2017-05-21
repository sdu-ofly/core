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
	 * @return
	 * 初始化角色管理界面
	 */
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	
	/**
	 * @return
	 * 新增角色界面
	 */
	@RequestMapping("/addRole")
	public String addRole() {
		return PATH_ADD_ROLE;
	}
	
	/**
	 * @param m		：数据绑定对象
	 * @param id	: 角色ID
	 * @return
	 */
	@RequestMapping("/editRole")
	public String editRole(Model m, String id) {
		RoleVo vo = roleService.queryRoleByPrimarykey(id);
		m.addAttribute("role", vo);
		return PATH_ADD_ROLE;
	}
	
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
	 * @param vo	: 角色数据
	 * @return
	 * 保存对应的角色数据
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public Map<String, Object> saveRole(RoleVo vo) {
		Map<String, Object> result = roleService.saveRole(vo);
		return result;
	}
	
	/**
	 * @param id	：角色ID
	 * @return
	 * 删除角色信息(根据ID)
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
	 * 查询角色管理的资源信息
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
	@RequestMapping("/deleteRelaResource")
	@ResponseBody
	public Map<String, Object> deleteRelaResource(String ids) {
		List<String> arr = JSONObject.parseArray(ids, String.class);
		Map<String, Object> result = resourcesService.deleteRelaResource(arr);
		return result;
	}
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
	@RequestMapping("/addRelaResource")
	@ResponseBody
	public Map<String, Object> addRelaResource(String data) {
		@SuppressWarnings("rawtypes")
		List<Map> arr = JSONObject.parseArray(data,Map.class);
		Map<String, Object> result = resourcesService.addRelaResource(arr);
		return result;
	}
}
