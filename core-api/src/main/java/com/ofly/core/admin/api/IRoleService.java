package com.ofly.core.admin.api;

import java.util.List;
import java.util.Map;

import com.ofly.core.admin.vo.RoleVo;

/**
 * @author Logan
 * 角色Api
 *
 */
public interface IRoleService {
	/**
	 * @param vo
	 * @return
	 * 保存角色信息
	 */
	Map<String, Object> saveRole(RoleVo vo);
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
	List<RoleVo> queryRoleList(Map<String, Object> params);
	/**
	 * @param params ：[
	 * 		'condition'	: 查询条件 【角色名称|角色编码】
	 * ]
	 * @return
	 * 查询角色列表总数量
	 */
	int queryRoleListNum(Map<String, Object> params);
	/**
	 * @param id
	 * @return
	 * 查询角色信息（根据id）
	 */
	RoleVo queryRoleByPrimarykey(String id);
	/**
	 * @param id
	 * @return
	 * 删除角色信息（根据ID）
	 */
	Map<String, Object> deleteRoleByPrimaryKey(String id);

}
