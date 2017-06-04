package com.ofly.core.admin.api;

import java.util.List;
import java.util.Map;

import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.RoleResRelaVo;
import com.ofly.core.admin.vo.TreeNode;

public interface IResourcesService {
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
	List<TreeNode> queryResources(String paretId);
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
	Map<String, Object> saveResourcesNode(TreeNode node);
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
	ResourceVo queryResourceByprimaryKey(String id);
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
	boolean hasResourcesChildNode(String id);
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
	Map<String, Object> deleteResourcesNode(String id);
	/**
	 * Introduction	：查询资源列表
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
	List<ResourceVo> queryList(Map<String, Object> params);
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
	int queryListNum(Map<String, Object> params);
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
	Map<String, Object> saveResource(ResourceVo vo);
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
	Map<String, Object> deleteResource(List<String> ids);
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
	List<RoleResRelaVo> queryExistResourceList(Map<String, Object> params);
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
	int queryExistResourceListNum(Map<String, Object> params);
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
	Map<String, Object> deleteRelaResource(List<String> ids);
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
	List<ResourceVo> queryUnUseResourceList(Map<String, Object> params);
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
	int queryUnUseResourceListNum(Map<String, Object> params);
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
	Map<String, Object> addRelaResource(@SuppressWarnings("rawtypes")List<Map> params);
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
	List<RoleResRelaVo> queryAllResourceAndRoles();

}
