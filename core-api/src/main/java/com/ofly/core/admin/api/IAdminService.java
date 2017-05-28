package com.ofly.core.admin.api;

import java.util.Map;

public interface IAdminService {
	/**
	 * @return
	 * 刷新权限角色资源
	 */
	Map<String, Object> refresh() throws Exception;

}
