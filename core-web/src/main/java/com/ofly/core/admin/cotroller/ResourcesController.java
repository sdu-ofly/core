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

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	private static final String PATH_INIT = "core/admin/resources"; 
	private static final String PATH_ADD_RESOURCE = "core/admin/addResource"; 
	@Autowired
	private IResourcesService resourcesService;
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	
	@RequestMapping("/addResource")
	public String addResource(Model m,String parentId, String parentName) {
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCE;
	}
	
	@RequestMapping("/editResource")
	public String editResource(Model m, String id, String parentId, String parentName) {
		ResourceVo vo = resourcesService.queryResourceByprimaryKey(id);
		m.addAttribute("vo", vo);
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCE;
	}
	
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
	
	@RequestMapping("/saveResource")
	@ResponseBody
	public Map<String, Object> saveResource(ResourceVo vo) {
		Map<String, Object> result = resourcesService.saveResource(vo);
		return result;
	}
	
	
}
