package com.ofly.core.admin.api;

import java.util.List;
import java.util.Map;

import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.RoleResRelaVo;
import com.ofly.core.admin.vo.TreeNode;

public interface IResourcesService {
	List<TreeNode> queryResources(String paretId);
	Map<String, Object> saveResourcesNode(TreeNode node);
	ResourceVo queryResourceByprimaryKey(String id);
	boolean hasResourcesChildNode(String id);
	Map<String, Object> deleteResourcesNode(String id);
	List<ResourceVo> queryList(Map<String, Object> params);
	int queryListNum(Map<String, Object> params);
	Map<String, Object> saveResource(ResourceVo vo);
	Map<String, Object> deleteResource(List<String> ids);
	List<RoleResRelaVo> queryExistResourceList(Map<String, Object> params);
	int queryExistResourceListNum(Map<String, Object> params);
	Map<String, Object> deleteRelaResource(List<String> ids);
	List<ResourceVo> queryUnUseResourceList(Map<String, Object> params);
	int queryUnUseResourceListNum(Map<String, Object> params);
	Map<String, Object> addRelaResource(@SuppressWarnings("rawtypes")List<Map> params);
	List<ResourceVo> queryAllResource();
	List<RoleResRelaVo> queryAllResourceAndRoles();

}
