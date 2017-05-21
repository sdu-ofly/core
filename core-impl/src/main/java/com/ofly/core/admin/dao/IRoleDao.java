package com.ofly.core.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.ofly.core.admin.vo.RoleVo;

/**
 * @author Logan
 * 角色Dao
 */
public interface IRoleDao {
	/**
	 * @return
	 * 新增角色
	 */
	int insertRole(RoleVo vo);
	/**
	 * @return
	 * 更新角色
	 */
	int updateRole(RoleVo vo);
	/**
	 * @param params
	 * @return
	 * 查询角色列表
	 */
	List<RoleVo> queryRoleList(Map<String, Object> params);
	/**
	 * @param params
	 * @return
	 * 查询角色列表数量
	 */
	int queryRoleListNum(Map<String, Object> params);
	/**
	 * @param id
	 * @return
	 * 查询角色信息（根据id）
	 */
	RoleVo queryRoleByPrimarykey(@Param("id")String id);
	/**
	 * @param id
	 * @return
	 * 删除角色信息（根据ID）
	 */
	int deleteRoleByPrimaryKey(@Param("id")String id) throws MySQLIntegrityConstraintViolationException;
	
}
