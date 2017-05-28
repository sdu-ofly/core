package com.ofly.core.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ofly.core.admin.vo.AccountRoleRelaVo;
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
	int deleteRoleByPrimaryKey(@Param("id")String id);
	/**
	 * @param params
	 * @return
	 * 查询关联角色（根据帐号ID）
	 */
	List<AccountRoleRelaVo> queryRelaRoleList(Map<String, Object> params);
	/**
	 * @param params
	 * @return
	 * 查询关联角色数量（根据帐号ID）
	 */
	int queryRelaRoleListNum(Map<String, Object> params);
	/**
	 * @param params
	 * @return
	 * 查询未分配的角色（根据帐号ID）
	 */
	List<RoleVo> queryUnUseRoleList(Map<String, Object> params);
	/**
	 * @param params
	 * @return
	 * 查询未分配角色数量(根据帐号ID)
	 */
	int queryUnUseRoleListNum(Map<String, Object> params);
	/**
	 * @param ids
	 * @return
	 * 删除关联角色
	 */
	int deleteRelaRole(@Param("ids")List<String> ids);
	/**
	 * @param data
	 * @return
	 * 新增管理角色
	 */
	int batchInsertReleRole(@SuppressWarnings("rawtypes") @Param("data")List<Map> data);
	/**
	 * @param account
	 * @return
	 * 查询角色编码列表(根据帐号)
	 */
	List<String> queryRoleValuesByAccount(@Param("account")String account);
	
}
