package com.ofly.core.admin.api;

import java.util.List;
import java.util.Map;

import com.ofly.core.admin.vo.AccountVo;

public interface IAccountService {
	List<AccountVo> queryAccountList(Map<String, Object> params);
	int queryAccountListNum(Map<String, Object> params);
	AccountVo queryAccountByPrimaryKey(String id);
	AccountVo queryAccountByAccount(String account);
	Map<String, Object> deleteAccount(String id);
	Map<String, Object> saveAccount(AccountVo vo);

}
