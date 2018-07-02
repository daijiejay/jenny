package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleRequest implements Serializable {

	/**
     * 角色代码
     */
	@ApiModelProperty(value = "角色代码", required = true)
    private Integer roleId;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

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
