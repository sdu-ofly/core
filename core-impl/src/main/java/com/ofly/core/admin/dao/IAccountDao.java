package com.ofly.core.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import com.ofly.core.admin.vo.AccountVo;

/**
 * @author Logan
 * 帐号Dao
 */
public interface IAccountDao {
	List<AccountVo> queryAccountList(Map<String, Object> params);
	int queryAccountListNum(Map<String, Object> params);
	AccountVo queryAccountByPrimaryKey(@Param("id")String id);
	int deleteAccount(@Param("id")String id);
	int insertAccount(AccountVo vo) throws DuplicateKeyException;
	int updateAccount(AccountVo vo);
}
