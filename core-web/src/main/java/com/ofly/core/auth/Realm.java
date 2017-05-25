package com.ofly.core.auth;

import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ofly.core.admin.api.IAccountService;
import com.ofly.core.admin.api.IRoleService;
import com.ofly.core.admin.vo.AccountVo;

public class Realm extends AuthorizingRealm {//
	
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IRoleService roleService;

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = (String) getAvailablePrincipal(principals);
		List<String> list = roleService.queryRoleValuesByAccount(account);
		HashSet<String> set = new HashSet<>(list);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(set);
		return info;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub  
        String username = (String) token.getPrincipal();  
        // 调用userService查询是否有此用户  
        //User user = null
        AccountVo vo=accountService.queryAccountByAccount(username);
        if (vo == null) {  
            // 抛出 帐号找不到异常  
            throw new UnknownAccountException();  
        }  
  
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
        		vo.getAccount(), // 用户名  
                vo.getPassword(), // 密码  
                ByteSource.Util.bytes(vo.getAccount()+vo.getSalt()),// salt=username+salt  
                getName() // realm name  
        );  
        return authenticationInfo;  
	}
	 

}
