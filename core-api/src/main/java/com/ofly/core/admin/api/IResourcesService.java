package com.ofly.core.admin.api;

import java.util.List;
import java.util.Map;

import com.ofly.core.admin.vo.TreeNode;

public interface IResourcesService {
	List<TreeNode> queryResources(String paretId);
	Map<String, Object> saveResourcesNode(TreeNode node);
	TreeNode queryResourceByprimaryKey(String id);
	boolean hasResourcesChildNode(String id);
	Map<String, Object> deleteResourcesNode(String id);

}
