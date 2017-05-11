package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.dao.IResourcesDao;
import com.ofly.core.admin.vo.TreeNode;

@Service
public class ResourcesService implements IResourcesService {
	
	@Autowired
	private IResourcesDao dao;
	@Override
	public List<TreeNode> queryResources(String parentId) {
		List<TreeNode> list = dao.queryResources(parentId);
		return list;
	}
	@Override
	public Map<String, Object> saveResourcesNode(TreeNode node) {
		Map<String, Object> result = new HashMap<>();
		int i=-1;
		if(node.getId()!=null &&node.getId()!="" ) {
			//修改
			i = dao.updateResourcesNode(node);
		} else {
			//新增
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
	@Override
	public TreeNode queryResourceByprimaryKey(String id) {
		TreeNode node = dao.queryResourceByprimaryKey(id);
		return node;
	}
	@Override
	public boolean hasResourcesChildNode(String id) {
		int i = dao.hasResourcesChildNode(id);
		return i==0?false:true;
	}
	@Override
	public Map<String, Object> deleteResourcesNode(String id) {
		Map<String, Object> result = new HashMap<>();
		int i = dao.deleteResourcesNode(id);
		result.put("code", i);
		if(i==0) {
			result.put("msg", "删除失败");
		} else {
			result.put("msg", "删除成功");
		}
		return result;
	}

}
