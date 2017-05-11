package com.ofly.core.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	TreeNode queryResourceByprimaryKey(@Param("id")String id);
	int updateResourcesNode(TreeNode node);
	int hasResourcesChildNode(@Param("id")String id);
	int deleteResourcesNode(@Param("id")String id);
}
