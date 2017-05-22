package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IRoleService;
import com.ofly.core.admin.dao.IRoleDao;
import com.ofly.core.admin.vo.AccountRoleRelaVo;
import com.ofly.core.admin.vo.RoleVo;
@Service
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao dao;
	/**
	 * @param vo
	 * @return
	 * 保存角色信息
	 */
	@Override
	public Map<String, Object> saveRole(RoleVo vo) {
		Map<String, Object> result=new HashMap<>();
		int i=0;
		if(vo.getId() == null || "".equals(vo.getId())) {
			//新增
			i = dao.insertRole(vo);
		} else {
			i = dao.updateRole(vo);
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
	 * @param params ：[
	 * 		'condition'	: 查询条件 【角色名称|角色编码】
	 * 		'rows'		: 一页行数
	 * 		'offset'	: 从第几行开始查询
	 * 		(后2个用于分页)
	 * ]
	 * @return
	 * 查询角色列表
	 * 	params 
	 * 
	 */
	@Override
	public List<RoleVo> queryRoleList(Map<String, Object> params) {
		List<RoleVo> list = dao.queryRoleList(params);
		return list;
	}
	/**
	 * @param params ：[
	 * 		'condition'	: 查询条件 【角色名称|角色编码】
	 * ]
	 * @return
	 * 查询角色列表总数量
	 */
	public int queryRoleListNum(Map<String, Object> params) {
		int num = dao.queryRoleListNum(params);
		return num;
	}
	/**
	 * @param id
	 * @return
	 * 查询角色信息（根据id）
	 */
	@Override
	public RoleVo queryRoleByPrimarykey(String id) {
		RoleVo vo = dao.queryRoleByPrimarykey(id);
		return vo;
	}
	/**
	 * @param id
	 * @return
	 * 删除角色信息(根据ID)
	 */
	@Override
	public Map<String, Object> deleteRoleByPrimaryKey(String id) {
		Map<String, Object> result = new HashMap<>();
		int i=0;
		try {
			i = dao.deleteRoleByPrimaryKey(id);
		} catch (Exception e) {
			result.put("code", i);
			result.put("msg", "该角色还有对应的资源,不能删除。");
			return result;
		}
		result.put("code", i);
		String msg = i==0?"删除失败":"删除成功";
		result.put("msg", msg);
		return result;
	}
	@Override
	public List<AccountRoleRelaVo> queryRelaRoleList(Map<String, Object> params) {
		List<AccountRoleRelaVo> list = dao.queryRelaRoleList(params);
		return list;
	}
	@Override
	public int queryRelaRoleListNum(Map<String, Object> params) {
		int num = dao.queryRelaRoleListNum(params);
		return num;
	}
	@Override
	public List<RoleVo> queryUnUseRoleList(Map<String, Object> params) {
		List<RoleVo> list = dao.queryUnUseRoleList(params);
		return list;
	}
	@Override
	public int queryUnUseRoleListNum(Map<String, Object> params) {
		int num = dao.queryUnUseRoleListNum(params);
		return num;
	}

}
