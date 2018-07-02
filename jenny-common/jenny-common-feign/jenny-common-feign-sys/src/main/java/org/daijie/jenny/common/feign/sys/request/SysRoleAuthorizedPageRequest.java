package org.daijie.jenny.common.feign.sys.request;

import org.daijie.core.result.Page;
import org.daijie.jdbc.mybatis.example.ExampleConditions;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleAuthorizedPageRequest extends Page implements ExampleConditions {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户编号")
	private Integer userId;
	
	@ApiModelProperty(value = "角色编号")
	private Integer roleId;
	
	@ApiModelProperty(value = "角色名称")
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
