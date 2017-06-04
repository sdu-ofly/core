package com.ofly.core.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import com.ofly.core.admin.vo.AccountVo;


/**
 * Introduction	：帐号Dao
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 下午1:03:42
 *
 */
public interface IAccountDao {
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
	List<AccountVo> queryAccountList(Map<String, Object> params);
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
	int queryAccountListNum(Map<String, Object> params);
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
	AccountVo queryAccountByPrimaryKey(@Param("id")String id);
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
	AccountVo queryAccountByAccount(@Param("account")String account);
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
	int deleteAccount(@Param("id")String id);
	/**
	 * Introduction	：插入帐号数据
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午1:06:00
	 * History		: 2017年6月4日 下午1:06:00   Logan715   Created.
	 * 
	 * @param vo	: 帐号信息
	 * @return		: 插入条数
	 * @throws DuplicateKeyException		: 帐号名重复时抛出异常
	 *
	 */
	int insertAccount(AccountVo vo) throws DuplicateKeyException;
	/**
	 * Introduction	：更新帐号信息
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午1:07:02
	 * History		: 2017年6月4日 下午1:07:02   Logan715   Created.
	 * 
	 * @param vo	: 帐号信息
	 * @return		: 更新条数
	 *
	 */
	int updateAccount(AccountVo vo);
}
