package com.ofly.core.admin.api;

import java.util.Map;

/**
 * Introduction	：后台管理Api
 *
 * Author		：Logan715                
 * Create Date	：2017年6月4日 上午10:41:20
 *
 */
public interface IAdminService {
	
	/**
	 * Introduction	：刷新资源权限
	 *
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 上午10:41:40
	 * History		: 2017年6月4日 上午10:41:40   Logan715   Created.
	 * 
	 * @return		:
	 * {
	 * 		code	:成功失败标识[成功:1;失败:0]
	 * 		msg 	: 提示信息
	 * }
	 * @throws Exception		
	 *
	 */
	Map<String, Object> refresh() throws Exception;

}
