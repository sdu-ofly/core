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

/**
 * Introduction	：树结构
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:19:04
 *
 */
@Controller
@RequestMapping("/tree")
public class TreeController {
	private static final String PATH_ADD_RESOURCES = "core/admin/resource/addResources";

	@Autowired
	private IResourcesService resourcesService;
	
	/**
	 * Introduction	：查询资源树
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:19:21
	 * History		: 2017年6月4日 上午10:19:21   Logan715   Created.
	 * 
	 * @param id	: 父节点Id
	 * @return		: List<TreeNode>
	 *
	 */
	@RequestMapping("queryResources")
	@ResponseBody
	public List<TreeNode> queryResources(String id) {
		List<TreeNode> list = resourcesService.queryResources(id);
		return list;
	}
	/**
	 * Introduction	：新增资源节点
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:20:19
	 * History		: 2017年6月4日 上午10:20:19   Logan715   Created.
	 * 
	 * @param m			: Model
	 * @param parentId	: 父节点Id
	 * @param parentName: 父节点名称
	 * @return			: 地址
	 *
	 */
	@RequestMapping("/addResourcesNode")
	public String addResourcesNode(Model m, String parentId,String parentName) {
		m.addAttribute("parentName", parentName);
		m.addAttribute("parentId", parentId);
		return PATH_ADD_RESOURCES;
	}
	
	/**
	 * Introduction	：修改资源节点信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:26:26
	 * History		: 2017年6月4日 上午10:26:26   Logan715   Created.
	 * 
	 * @param m		: Model
	 * @param id	: 资源节点Id
	 * @return		: 地址
	 *
	 */
	@RequestMapping("/editResourcesNode")
	public String editResourcesNode(Model m, String id) {
		ResourceVo vo = resourcesService.queryResourceByprimaryKey(id);
		m.addAttribute("node", vo);
		return PATH_ADD_RESOURCES;
	}
	
	/**
	 * Introduction	：保存资源节点
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:24:47
	 * History		: 2017年6月4日 上午10:24:47   Logan715   Created.
	 * 
	 * @param node	: 节点数据
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 */
	@RequestMapping("/saveResourcesNode")
	@ResponseBody
	public Map<String, Object> saveResourcesNode(TreeNode node) {
		 Map<String, Object> result = resourcesService.saveResourcesNode(node);
		return result;
	}
	
	/**
	 * Introduction	：是否有资源子节点
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:24:45
	 * History		: 2017年6月4日 上午10:24:45   Logan715   Created.
	 * 
	 * @param id
	 * @return		: (true|false)
	 *
	 */
	@RequestMapping("/hasResourcesChildNode")
	@ResponseBody
	public boolean hasResourcesChildNode(String id) {
		boolean flag = resourcesService.hasResourcesChildNode(id);
		return flag;
	}
	
	/**
	 * Introduction	：删除资源节点信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:22:38
	 * History		: 2017年6月4日 上午10:22:38   Logan715   Created.
	 * 
	 * @param id	: 节点Id
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@RequestMapping("/deleteResourcesNode")
	@ResponseBody
	public Map<String, Object> deleteResourcesNode(String id) {
		Map<String, Object> result = resourcesService.deleteResourcesNode(id);
		return result;
	}
	
	
}
