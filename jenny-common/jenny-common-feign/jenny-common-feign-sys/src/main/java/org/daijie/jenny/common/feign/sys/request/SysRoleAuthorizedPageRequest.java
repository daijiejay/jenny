package org.daijie.jenny.common.feign.sys.request;

import org.daijie.jdbc.mybatis.example.ExampleExecutePage;
import org.daijie.jenny.common.feign.sys.response.SysRoleAuthorizedReponse;
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleAuthorizedPageRequest extends ExampleExecutePage<SysRoleAuthorized, SysRoleAuthorizedReponse> {

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
