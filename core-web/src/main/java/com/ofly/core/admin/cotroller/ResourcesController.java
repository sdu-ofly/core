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
import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.vo.ResourceVo;

/**
 * Introduction	：资源管理
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午9:56:49
 *
 */
@Controller
@RequestMapping("/admin/resources")
public class ResourcesController {
	private static final String PATH_INIT = "core/admin/resource/resources"; 
	private static final String PATH_ADD_RESOURCE = "core/admin/resource/addResource"; 
	@Autowired
	private IResourcesService resourcesService;
	/**
	 * Introduction	：初始化资源管理界面
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:57:14
	 * History		: 2017年6月4日 上午9:57:14   Logan715   Created.
	 * 
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	
	/**
	 * Introduction	：初始化新增资源界面
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:57:39
	 * History		: 2017年6月4日 上午9:57:39   Logan715   Created.
	 * 
	 * @param m			: Model
	 * @param parentId	: 父节点Id
	 * @param parentName: 父节点名称
	 * @return			: 地址
	 *
	 */
	@RequestMapping("/addResource")
	public String addResource(Model m,String parentId, String parentName) {
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCE;
	}
	
	/**
	 * Introduction	：初始化编辑资源信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午9:58:56
	 * History		: 2017年6月4日 上午9:58:56   Logan715   Created.
	 * 
	 * @param m			: Model
	 * @param id		: 资源Id
	 * @param parentId	: 父节点Id
	 * @param parentName: 父节点名称
	 * @return			: 地址
	 *
	 */
	@RequestMapping("/editResource")
	public String editResource(Model m, String id, String parentId, String parentName) {
		ResourceVo vo = resourcesService.queryResourceByprimaryKey(id);
		m.addAttribute("vo", vo);
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCE;
	}
	
	/**
	 * Introduction	：查询资源列表
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:00:01
	 * History		: 2017年6月4日 上午10:00:01   Logan715   Created.
	 * 
	 * @param parentId	: 父节点Id
	 * @param condition	: (资源名称|资源路径)可模糊查询
	 * @param page		: 页数
	 * @param rows		: 显示行数
	 * @return			: 资源List的JSON数据
	 *
	 */
	@RequestMapping("/queryList")
	@ResponseBody
	public JSONObject queryList(String parentId, String condition,Integer page, Integer rows) {
		HashMap<String, Object> params=new HashMap<>();
		params.put("parentId",parentId);
		params.put("condition",condition);
		params.put("rows", rows);
		params.put("offset", (page-1)*rows);
		JSONObject result = new JSONObject();
		List<ResourceVo> list = resourcesService.queryList(params);
		int num = resourcesService.queryListNum(params);
		result.put("total", num);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * Introduction	：保存资源
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:01:40
	 * History		: 2017年6月4日 上午10:01:40   Logan715   Created.
	 * 
	 * @param vo	: 资源数据
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/saveResource")
	@ResponseBody
	public Map<String, Object> saveResource(ResourceVo vo) {
		Map<String, Object> result = resourcesService.saveResource(vo);
		return result;
	}
	
	/**
	 * Introduction	：删除资源
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:02:28
	 * History		: 2017年6月4日 上午10:02:28   Logan715   Created.
	 * 
	 * @param ids	: 资源Id数组Json字符串
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/deleteResource")
	@ResponseBody
	public Map<String, Object> deleteResource(String ids) {
		List<String> arr = JSONObject.parseArray(ids, String.class);
		Map<String, Object> result = resourcesService.deleteResource(arr);
		return result;
	}
	
	
}
