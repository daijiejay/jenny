package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import org.daijie.core.result.Page;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRolePageRequest extends Page implements Serializable {

	/**
     * 角色代码
     */
	@ApiModelProperty(value = "角色代码", required = true)
    private String roleCode;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
