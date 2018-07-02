package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleAuthorizedReponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户编号", required = true)
	private Integer userId;
	
	@ApiModelProperty(value = "角色编号", required = true)
	private Integer roleId;
	
	@ApiModelProperty(value = "角色名称", required = true)
	private String roleName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
