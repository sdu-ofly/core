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
@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountDao dao;
	@Override
	public List<AccountVo> queryAccountList(Map<String, Object> params) {
		List<AccountVo> list = dao.queryAccountList(params);
		return list;
	}

	@Override
	public int queryAccountListNum(Map<String, Object> params) {
		int num = dao.queryAccountListNum(params);
		return num;
	}

	@Override
	public AccountVo queryAccountByPrimaryKey(String id) {
		AccountVo accountVo = dao.queryAccountByPrimaryKey(id);
		return accountVo;
	}

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

	@Override
	public Map<String, Object> saveAccount(AccountVo vo) {
		Map<String, Object> result = null;
		if(vo.getId()!=null &&!"".equals(vo.getId())) {// 修改
			result = updateAvvount(vo);
		} else {// 新增
			result = insertAvvount(vo);
		}
		return result;
	}
	private Map<String, Object> insertAvvount(AccountVo vo) {
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
	

}
