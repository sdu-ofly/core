package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.dao.IResourcesDao;
import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.RoleResRelaVo;
import com.ofly.core.admin.vo.TreeNode;

/**
 * Introduction	：资源管理Service
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:44:36
 *
 */
@Service
public class ResourcesService implements IResourcesService {
	
	@Autowired
	private IResourcesDao dao;
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
	@Override
	public List<TreeNode> queryResources(String parentId) {
		List<TreeNode> list = dao.queryResources(parentId);
		return list;
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
	@Override
	public Map<String, Object> saveResourcesNode(TreeNode node) {
		Map<String, Object> result = new HashMap<>();
		int i=-1;
		if(node.getId()!=null &&node.getId()!="" ) {//修改
			i = dao.updateResourcesNode(node);
		} else {//新增
			i = dao.saveResourcesNode(node);
		}
		result.put("code", i);
		if(i==0) {
			result.put("msg", "保存不成功");
		} else {
			result.put("msg", "保存成功");
		}
		return result;
	}
	/**
	 * Introduction	：根据主键查询资源信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:46:18
	 * History		: 2017年6月4日 上午10:46:18   Logan715   Created.
	 * 
	 * @param id	: 资源Id
	 * @return		: ResourceVo
	 *
	 */
	@Override
	public ResourceVo queryResourceByprimaryKey(String id) {
		ResourceVo vo = dao.queryResourceByprimaryKey(id);
		return vo;
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
	@Override
	public boolean hasResourcesChildNode(String id) {
		int i = dao.hasResourcesChildNode(id);
		return i==0?false:true;
	}
	/**
	 * Introduction	：删除资源树节点
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:55:55
	 * History		: 2017年6月4日 下午12:55:55   Logan715   Created.
	 * 
	 * @param id	: 资源树节点Id
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@Override
	public Map<String, Object> deleteResourcesNode(String id)  {
		Map<String, Object> result = new HashMap<>();
		int i=0;
		i = dao.deleteResourcesNode(id);
		result.put("code", i);
		if(i==0) {
			result.put("code", i);
			result.put("msg", "删除失败");
		} else {
			result.put("code", i);
			result.put("msg", "删除成功");
		}
		return result;
	}
	/**
	 * Introduction	：查询资源信息List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:56:39
	 * History		: 2017年6月4日 下午12:56:39   Logan715   Created.
	 * 
	 * @param params:
	 * {
	 * 		parentId	: 父节点Id
	 * 		condition	: (资源名称|资源路径)可模糊查询
	 * 		rows		: 显示行数
	 * 		offset		:  偏移量(从第几条数据查询起)
	 * }
	 * @return		: List<ResourceVo>
	 *
	 */
	@Override
	public List<ResourceVo> queryList(Map<String, Object> params) {
		List<ResourceVo> list = dao.queryList(params);
		return list;
	}
	/**
	 * Introduction	：查询资源列表数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:56:39
	 * History		: 2017年6月4日 下午12:56:39   Logan715   Created.
	 * 
	 * @param params:
	 * {
	 * 		parentId	: 父节点Id
	 * 		condition	: (资源名称|资源路径)可模糊查询
	 * }
	 * @return		: 数量
	 *
	 */
	@Override
	public int queryListNum(Map<String, Object> params) {
		int num = dao.queryListNum(params);
		return num;
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
	@Override
	public Map<String, Object> saveResource(ResourceVo vo) {
		Map<String, Object> result = new HashMap<>();
		int i = 0;
		if(vo.getId()==null ||"".equals(vo.getId())) {
			i = dao.saveResource(vo);
		} else {
			i = dao.updateResource(vo);
		}
		result.put("code", i);
		if(i==0) {
			result.put("msg", "保存失败");
		} else {
			result.put("msg", "保存成功");
		}
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
	@Override
	public Map<String, Object> deleteResource(List<String> ids) {
		Map<String, Object> result = new HashMap<>();
		int i=0;
		try {
			i = dao.deleteResource(ids);
		} catch (Exception e) {
			result.put("code", 0);
			result.put("msg", "角色资源管理表有外键数据没删除~请确认");
			return result;
		}
		result.put("code", i==0?0:1);
		if(i==0) {
			result.put("msg", "删除失败");
		} else {
			result.put("msg", "删除成功");
		}
		return result;
	}
	/**
	 * Introduction	：查询角色关联的资源List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:48:28
	 * History		: 2017年6月4日 上午10:48:28   Logan715   Created.
	 * 
	 * @param params
	 * {
	 * 		condition 	: (资源名称|资源路径)可模糊查询
	 * 		roleId		: 角色Id
	 * 		rows		: 查询行数
	 * 		offset		: 偏移量(从第几条数据开始查询起)
	 * }
	 * @return		: List<RoleResRelaVo>
	 *
	 */
	@Override
	public List<RoleResRelaVo> queryExistResourceList(Map<String, Object> params) {
		List<RoleResRelaVo> list = dao.queryExistResourceList(params);
		return list;
	}
	/**
	 * Introduction	：查询角色关联的资源数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:48:28
	 * History		: 2017年6月4日 上午10:48:28   Logan715   Created.
	 * 
	 * @param params
	 * {
	 * 		condition 	: (资源名称|资源路径)可模糊查询
	 * 		roleId		: 角色Id
	 * }
	 * @return			: 数量
	 *
	 */
	@Override
	public int queryExistResourceListNum(Map<String, Object> params) {
		int i = dao.queryExistResourceListNum(params);
		return i;
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
	@Override
	public Map<String, Object> deleteRelaResource(List<String> ids) {
		Map<String, Object> result = new HashMap<>();
		int i = dao.deleteRelaResource(ids);
		int code= i==0?0:1;
		String msg = i==0?"删除失败":"删除成功";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	/**
	 * Introduction	：查询角色未管理的数据List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:52:34
	 * History		: 2017年6月4日 上午10:52:34   Logan715   Created.
	 * 
	 * @param params
	 * {
	 * 		roleId	: 角色Id
	 * 		parentId: 父节点Id
	 * 		rows	: 显示行数
	 * 		offset	: 偏移量(从第几条数据开始查询起)
	 * }
	 * @return		: List<ResourceVo>
	 *
	 */
	@Override
	public List<ResourceVo> queryUnUseResourceList(Map<String, Object> params) {
		List<ResourceVo> list = dao.queryUnUseResourceList(params);
		return list;
	}
	/**
	 * Introduction	：查询角色未管理的数据数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:52:34
	 * History		: 2017年6月4日 上午10:52:34   Logan715   Created.
	 * 
	 * @param params
	 * {
	 * 		roleId	: 角色Id
	 * 		parentId: 父节点Id
	 * }
	 * @return		: 数量
	 *
	 */
	@Override
	public int queryUnUseResourceListNum(Map<String, Object> params) {
		int num = dao.queryUnUseResourceListNum(params);
		return num;
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
	@Override
	public Map<String, Object> addRelaResource(@SuppressWarnings("rawtypes") List<Map> params) {
		Map<String, Object> result = new HashMap<>();
		int i = dao.batchInsertRoleResRela(params);
		int code= i==0?0:1;
		String msg = i==0?"添加失败":"添加成功";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	/**
	 * Introduction	：查询所有资源和资源多对应的角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午1:01:54
	 * History		: 2017年6月4日 下午1:01:54   Logan715   Created.
	 * 
	 * @return		: List<RoleResRelaVo>
	 *
	 */
	@Override
	public List<RoleResRelaVo> queryAllResourceAndRoles() {
		List<RoleResRelaVo> list = dao.queryAllResourceAndRoles();
		return list;
	}
	
		
}
