package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleUpdateRequest implements Serializable {

	/**
     * 角色代码
     */
	@ApiModelProperty(value = "角色代码", required = true)
    private Integer roleId;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 是否禁用
     */
	@ApiModelProperty(value = "是否禁用")
    private Boolean enable;

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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
