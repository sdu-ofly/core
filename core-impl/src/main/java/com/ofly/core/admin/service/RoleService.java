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
/**
 * Introduction	：角色管理Service
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:57:59
 *
 */
@Service
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao dao;
	/**
	 * Introduction	：保存角色信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:58:14
	 * History		: 2017年6月4日 上午10:58:14   Logan715   Created.
	 * 
	 * @param vo	: 角色数据
	 * @return		: 	 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
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
	 * Introduction	：查询角色列表
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:59:02
	 * History		: 2017年6月4日 上午10:59:02   Logan715   Created.
	 * 
	 * @param params: 
	 * {
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * 		rows		: 一页行数
	 * 		offset		: 从第几行开始查询
	 * 		(后2个用于分页)
	 * }
	 * @return		: List<RoleVo>
	 *
	 */
	@Override
	public List<RoleVo> queryRoleList(Map<String, Object> params) {
		List<RoleVo> list = dao.queryRoleList(params);
		return list;
	}
	/**
	 * Introduction	：查询角色列表总数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午11:00:37
	 * History		: 2017年6月4日 上午11:00:37   Logan715   Created.
	 * 
	 * @param params: 
	 * {
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * }
	 * @return			: 数量
	 *
	 */
	public int queryRoleListNum(Map<String, Object> params) {
		int num = dao.queryRoleListNum(params);
		return num;
	}
	/**
	 * Introduction	：根据主键查询角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午11:01:17
	 * History		: 2017年6月4日 上午11:01:17   Logan715   Created.
	 * 
	 * @param id	: 角色Id
	 * @return		: RoleVo
	 *
	 */
	@Override
	public RoleVo queryRoleByPrimarykey(String id) {
		RoleVo vo = dao.queryRoleByPrimarykey(id);
		return vo;
	}
	/**
	 * Introduction	：根据主键删除角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午11:01:55
	 * History		: 2017年6月4日 上午11:01:55   Logan715   Created.
	 * 
	 * @param id	: 角色Id
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
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
	/**
	 * Introduction	：查询帐号管理的角色信息List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午11:03:01
	 * History		: 2017年6月4日 上午11:03:01   Logan715   Created.
	 * 
	 * @param params: 
	 * {
	 * 		accountId	: 帐号Id
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * 		rows		: 显示行数
	 * 		offset		: 偏移量(从第几条数据开始查询起)
	 * }
	 * @return			: List<AccountRoleRelaVo>
	 *
	 */
	@Override
	public List<AccountRoleRelaVo> queryRelaRoleList(Map<String, Object> params) {
		List<AccountRoleRelaVo> list = dao.queryRelaRoleList(params);
		return list;
	}
	/**
	 * Introduction	：查询帐号管理的角色信息数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午11:03:01
	 * History		: 2017年6月4日 上午11:03:01   Logan715   Created.
	 * 
	 * @param params: 
	 * {
	 * 		accountId	: 帐号Id
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * }
	 * @return			: 数量
	 *
	 */
	@Override
	public int queryRelaRoleListNum(Map<String, Object> params) {
		int num = dao.queryRelaRoleListNum(params);
		return num;
	}
	/**
	 * Introduction	：查询帐号未被使用的角色List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:45:43
	 * History		: 2017年6月4日 下午12:45:43   Logan715   Created.
	 * 
	 * @param params：
	 * {
	 * 		accountId	: 帐号Id
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * 		rows		: 显示行数
	 * 		offset		: 偏移了(从第几条数据查询起)
	 * }
	 * @return		: List<RoleVo>
	 *
	 */
	@Override
	public List<RoleVo> queryUnUseRoleList(Map<String, Object> params) {
		List<RoleVo> list = dao.queryUnUseRoleList(params);
		return list;
	}
	/**
	 * Introduction	：查询帐号未被使用的角色List
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:45:43
	 * History		: 2017年6月4日 下午12:45:43   Logan715   Created.
	 * 
	 * @param params：
	 * {
	 * 		accountId	: 帐号Id
	 * 		condition	: (角色名称|角色编码)可模糊查询
	 * }
	 * @return		: List<RoleVo>
	 *
	 */
	@Override
	public int queryUnUseRoleListNum(Map<String, Object> params) {
		int num = dao.queryUnUseRoleListNum(params);
		return num;
	}
	/**
	 * Introduction	：删除帐号关联的角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:49:32
	 * History		: 2017年6月4日 下午12:49:32   Logan715   Created.
	 * 
	 * @param ids	:关联角色List
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识(成功:1;失败:0)
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@Override
	public Map<String, Object> deleteRelaRole(List<String> ids) {
		Map<String, Object> result = new HashMap<>();
		int i = dao.deleteRelaRole(ids);
		String code = i==0?"0":"1";
		String msg = i==0?"删除失败":"删除成功";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	/**
	 * Introduction	：新增管理角色
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:51:02
	 * History		: 2017年6月4日 下午12:51:02   Logan715   Created.
	 * 
	 * @param data	: 管理角色信息List
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识(成功:1;失败:0)
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@Override
	public Map<String, Object> addReleRole(@SuppressWarnings("rawtypes") List<Map> data) {
		Map<String, Object> result = new HashMap<>();
		int i = dao.batchInsertReleRole(data);
		String code = i==0?"0":"1";
		String msg = i==0?"添加失败":"添加成功";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	/**
	 * Introduction	：查询帐号管理的角色编码
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午12:51:53
	 * History		: 2017年6月4日 下午12:51:53   Logan715   Created.
	 * 
	 * @param account	: 帐号名称
	 * @return			: List<String> --> 角色编码List
	 *
	 */
	@Override
	public List<String> queryRoleValuesByAccount(String account) {
		List<String> list = dao.queryRoleValuesByAccount(account);
		return list;
	}

}
