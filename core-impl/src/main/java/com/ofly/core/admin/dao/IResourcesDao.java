package com.ofly.core.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ofly.core.admin.vo.ResourceVo;
import com.ofly.core.admin.vo.RoleResRelaVo;
import com.ofly.core.admin.vo.TreeNode;

public interface IResourcesDao {
	/**
	 * @param parentId
	 * 根据父节点ID查询子节点信息
	 * 
	 * @return
	 */
	List<TreeNode> queryResources(@Param("parentId")String parentId);
	int saveResourcesNode(TreeNode node);
	ResourceVo queryResourceByprimaryKey(@Param("id")String id);
	int updateResourcesNode(TreeNode node);
	int hasResourcesChildNode(@Param("id")String id);
	int deleteResourcesNode(@Param("id")String id);
	List<ResourceVo> queryList(Map<String, Object> params);
	int queryListNum(Map<String, Object> params);
	int saveResource(ResourceVo vo);
	int updateResource(ResourceVo vo);
	int deleteResource(@Param("ids")List<String> ids);
	List<RoleResRelaVo> queryExistResourceList(Map<String, Object> params) ;
	int queryExistResourceListNum(Map<String, Object> params) ;
	int deleteRelaResource(@Param("ids")List<String> ids);
	List<ResourceVo> queryUnUseResourceList(Map<String, Object> params);
	int queryUnUseResourceListNum(Map<String, Object> params);
	int batchInsertRoleResRela(@SuppressWarnings("rawtypes") @Param("arr")List<Map> params);
	List<ResourceVo> queryAllResource();
	List<RoleResRelaVo> queryAllResourceAndRoles();
}
