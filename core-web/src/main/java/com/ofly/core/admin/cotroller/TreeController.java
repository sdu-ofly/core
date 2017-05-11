package com.ofly.core.admin.cotroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.TreeNode;

@Controller
@RequestMapping("/tree")
public class TreeController {
	private static final String PATH_ADD_RESOURCES = "core/admin/addResources";

	@Autowired
	private IResourcesService resourcesService;
	
	@RequestMapping("queryResources")
	@ResponseBody
	public List<TreeNode> queryResources(String id) {
		List<TreeNode> list = resourcesService.queryResources(id);
		return list;
	}
	@RequestMapping("/addResourcesNode")
	public String addResourcesNode(Model m, String parentId,String parentName) {
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCES;
	}
	
	@RequestMapping("/editResourcesNode")
	public String editResourcesNode(Model m, String id) {
		ResourceVo vo = resourcesService.queryResourceByprimaryKey(id);
		m.addAttribute("node", vo);
		return PATH_ADD_RESOURCES;
	}
	
	@RequestMapping("/saveResourcesNode")
	@ResponseBody
	public Map<String, Object> saveResourcesNode(TreeNode node) {
		 Map<String, Object> result = resourcesService.saveResourcesNode(node);
		return result;
	}
	
	@RequestMapping("/hasResourcesChildNode")
	@ResponseBody
	public boolean hasResourcesChildNode(String id) {
		boolean flag = resourcesService.hasResourcesChildNode(id);
		return flag;
	}
	
	@RequestMapping("/deleteResourcesNode")
	@ResponseBody
	public Map<String, Object> deleteResourcesNode(String id) {
		Map<String, Object> result = resourcesService.deleteResourcesNode(id);
		return result;
	}
	
	
}
