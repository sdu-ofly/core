package com.ofly.core.auth;

import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ofly.core.admin.api.IResourcesService;
import com.ofly.core.admin.vo.RoleResRelaVo;

/**
 * Author		：Logan715                
 * Create Date	：2017年6月4日 下午6:19:15
 *
 * Introduction	：权限管理
 */
public class AuthenManager {
	@Autowired
	private IResourcesService resourcesService;
	private static final String CRLF = "\t\n";
	@Autowired
	@Qualifier("shiroFilter")
	private ShiroFilterFactoryBean shiroFilter;
	private static AuthenManager instance;
	
	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:21:18
	 * History		: 2017年6月4日 下午6:21:18   Logan715   Created.
	
	 * Introduction	：获取权限管理实例
	 * @return		: AuthenManager
	 *
	 */
	public static AuthenManager getInstance(){
		if(instance==null) {
			synchronized (AuthenManager.class) {
				if(instance==null) {
					instance = new AuthenManager();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:21:58
	 * History		: 2017年6月4日 下午6:21:58   Logan715   Created.
	
	 * Introduction	：重新加载权限角色资源信息
	 * @return
	 * @throws Exception		: 
	 *
	 */
	public boolean reloadFilterChainDefinitions() throws Exception {
		/*=============================================================
		//TODO  当多人点击刷新权限角色的时候可能会出现问题。 得重新设计
				从前台点击时eg：第一个人点击后， 第二个人也点击了，传一个参数 
				表示资源权限角色 不是最新的了。终止第一个人的操作。
				让第二个人执行该方法。记得用锁
				
		 ==============================================================*/
		if(shiroFilter == null) { 
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			shiroFilter = (ShiroFilterFactoryBean)wac.getBean(ShiroFilterFactoryBean.class);
		}
		AbstractShiroFilter shiro = (AbstractShiroFilter)shiroFilter.getObject();
		PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver)shiro.getFilterChainResolver();
		DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)pathMatchingFilterChainResolver.getFilterChainManager();
		filterChainManager.getFilterChains().clear();
		shiroFilter.getFilterChainDefinitionMap().clear();
		shiroFilter.setFilterChainDefinitions(this.getFilterChainDefinitions());
		Map<String, String> chains = shiroFilter.getFilterChainDefinitionMap();  
        for (Map.Entry<String, String> entry : chains.entrySet()) {  
            String url = entry.getKey();  
            String chainDefinition = entry.getValue().trim().replace(" ", "");  
            filterChainManager.createChain(url, chainDefinition);
        } 
		return true;
	}
	
	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:24:12
	 * History		: 2017年6月4日 下午6:24:12   Logan715   Created.
	
	 * Introduction	：加载权限过滤器
	 * @return		: shiro 权限字符串
	 *
	 */
	public String loadFilterChainDefinitions() {
		return getFilterChainDefinitions();
	}
	
	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:25:00
	 * History		: 2017年6月4日 下午6:25:00   Logan715   Created.
	
	 * Introduction	：获取权限字符串
	 * @return		: 返回权限字符串
	 *
	 */
	private String getFilterChainDefinitions() {
		
		if(resourcesService==null) {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			resourcesService = (IResourcesService)wac.getBean("resourcesService");
		}
		
		StringBuffer sb = new StringBuffer();
		List<RoleResRelaVo> list = resourcesService.queryAllResourceAndRoles();
		if(list!=null && !list.isEmpty()) {
			for (RoleResRelaVo vo : list) {
				if("1".equals(vo.getAuth())) {
					sb.append(vo.getUrl()).append(" = ").append("authc").append(",role["+vo.getRoleValues()+"]").append(CRLF);
			} else {
					sb.append(vo.getUrl()).append(" = ").append("anon").append(CRLF);
				}
			}
		}
		return sb.toString();
	}

}
