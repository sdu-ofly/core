package com.ofly.core.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * Author		：Logan715                
 * Create Date	：2017年6月4日 下午6:25:41
 * Introduction	：角色权限过滤器
 *
 */
public class RoleAuthorizationFilter extends AuthorizationFilter {

	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:26:13
	 * History		: 2017年6月4日 下午6:26:13   Logan715   Created.
	
	 * Introduction	：权限是否允许
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return		:(true|false)
	 * @throws Exception		: TODO 入参出参说明下
	 *
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}
		for (String  roleValue : rolesArray) {
			if(subject.hasRole(roleValue)) {
				return true;
			}
		}
		return false;
	}

}
