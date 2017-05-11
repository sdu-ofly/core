package com.ofly.core.admin.vo;

public class ResourceVo {
	private String id;			// 主键ID
	private String parentId;	// 父节点ID
	private String name;		// 资源名称
	private String url;			// 资源路径
	private String type;		// 资源类型  01：文件夹 02：方法
	private String isMenu;		// 是否可配置成菜单项
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	
	
	

}
