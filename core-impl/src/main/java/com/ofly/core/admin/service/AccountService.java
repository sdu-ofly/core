package com.ofly.core.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.ofly.core.admin.api.IAccountService;
import com.ofly.core.admin.dao.IAccountDao;
import com.ofly.core.admin.vo.AccountVo;
import com.ofly.core.utils.EndecryptUtils;
/**
 * Introduction	：帐号Service
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:29:12
 *
 */
@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountDao dao;
	/**
	 * Introduction	：查询帐号列表
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:29:33
	 * History		: 2017年6月4日 上午10:29:33   Logan715   Created.
	 *
	 * @param params	:
	 * {
	 * 		condition	: (帐号名称|用户名称)可模糊查询
	 * 		rows		: 显示行数
	 * 		offset		: 偏移量(第几个数据开始)
	 * }
	 * @return			: List<AccountVo>
	 *
	 */
	@Override
	public List<AccountVo> queryAccountList(Map<String, Object> params) {
		List<AccountVo> list = dao.queryAccountList(params);
		return list;
	}

	/**
	 * Introduction	：查询帐号数量
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:32:25
	 * History		: 2017年6月4日 上午10:32:25   Logan715   Created.
	 *
	 * @param params	: 
	 * {
	 * 		condition	: (帐号名称|用户名称)可模糊查询
	 * }
	 * @return			: 数量
	 *
	 */
	@Override
	public int queryAccountListNum(Map<String, Object> params) {
		int num = dao.queryAccountListNum(params);
		return num;
	}

	/**
	 * Introduction	：根据主键查询帐号
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:33:44
	 * History		: 2017年6月4日 上午10:33:44   Logan715   Created.
	 *
	 * @param id	: 帐号Id
	 * @return		: AccountVo
	 *
	 */
	@Override
	public AccountVo queryAccountByPrimaryKey(String id) {
		AccountVo accountVo = dao.queryAccountByPrimaryKey(id);
		return accountVo;
	}

	/**
	 * Introduction	：删除帐号
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:34:23
	 * History		: 2017年6月4日 上午10:34:23   Logan715   Created.
	 *
	 * @param id	: 帐号Id
	 * @return
	 *
	 */
	@Override
	public Map<String, Object> deleteAccount(String id) {
		Map<String, Object> result = new HashMap<>();
		int i = 0;
		try {
			i = dao.deleteAccount(id);
		} catch (DataIntegrityViolationException e) {
			result.put("code", "0");
			result.put("msg", "该帐号拥有角色,不能删除");
			return result;
		}
		String code = i==0?"0":"1";
		String msg = i==0?"删除失败":"删除成功";
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

	/**
	 * Introduction	：保存帐号
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:34:50
	 * History		: 2017年6月4日 上午10:34:50   Logan715   Created.
	 *
	 * @param vo	: 帐号信息
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	@Override
	public Map<String, Object> saveAccount(AccountVo vo) {
		Map<String, Object> result = null;
		if(vo.getId()!=null &&!"".equals(vo.getId())) {// 修改
			result = updateAvvount(vo);
		} else {// 新增
			result = insertAccount(vo);
		}
		return result;
	}
	/**
	 * Introduction	插入帐号数据
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:36:53
	 * History		: 2017年6月4日 上午10:36:53   Logan715   Created.
	 * 
	 * @param vo	: 帐号数据
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	private Map<String, Object> insertAccount(AccountVo vo) {
		Map<String, Object> result = new HashMap<>();
		EndecryptUtils.md5Password(vo);
		int i=0;
		try {
			i = dao.insertAccount(vo);
		} catch (DuplicateKeyException e) {
			result.put("code", 0);
			result.put("msg", "帐号已经存在,请重新输入");
			return result;
		}
		String msg = i==0?"保存失败":"保存成功";
		result.put("code", i);
		result.put("msg", msg);
		return result;
	}
	/**
	 * Introduction	：更新帐号数据
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:37:53
	 * History		: 2017年6月4日 上午10:37:53   Logan715   Created.
	 * 
	 * @param vo	: 帐号数据
	 * @return		: 
	 * {
	 * 		code	: 成功失败标识[成功:1;失败：0]
	 * 		msg		: 提示信息
	 * }
	 *
	 */
	private Map<String, Object> updateAvvount(AccountVo vo) {
		Map<String, Object> result = new HashMap<>();
		if(vo.getPassword()!=null && !"".equals(vo.getPassword())) {// 密码存在，需要修改密码
			EndecryptUtils.md5Password(vo);
		} else {
			vo.setPassword(null);
			vo.setSalt(null);
		}
		int i = dao.updateAccount(vo);
		String msg = i==0?"保存失败":"保存成功";
		result.put("code", i);
		result.put("msg", msg);
		return result;
	}

	/**
	 * Introduction	：根据帐号名称查询帐号信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:39:29
	 * History		: 2017年6月4日 上午10:39:29   Logan715   Created.
	 * 
	 * @param account	: 帐号名称
	 * @return			: AccountVo
	 *
	 */
	@Override
	public AccountVo queryAccountByAccount(String account) {
		AccountVo vo = dao.queryAccountByAccount(account);
		return vo;
	}
	

}
