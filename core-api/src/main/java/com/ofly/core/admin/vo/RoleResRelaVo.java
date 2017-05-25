package com.ofly.core.admin.vo;

public class RoleResRelaVo extends ResourceVo {
	private String roleResRelaId;	// 角色资源关联ID
	private String roleValues;		// 角色编码（含多个用,隔开）

	public String getRoleValues() {
		return roleValues;
	}

	public void setRoleValues(String roleValues) {
		this.roleValues = roleValues;
	}

	public String getRoleResRelaId() {
		return roleResRelaId;
	}

	public void setRoleResRelaId(String roleResRelaId) {
		this.roleResRelaId = roleResRelaId;
	}
	

}
