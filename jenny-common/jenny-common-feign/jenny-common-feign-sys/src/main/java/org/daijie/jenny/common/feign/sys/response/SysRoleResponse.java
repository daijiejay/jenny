package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleResponse implements Serializable {
    /**
     * 角色编号
     */
	@ApiModelProperty(value = "角色编号", required = true)
    private Integer roleId;

	/**
     * 角色代码
     */
	@ApiModelProperty(value = "角色代码", required = true)
    private Integer roleCode;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    /**
     * 是否禁用
     */
	@ApiModelProperty(value = "角色代码", required = true)
    private Boolean enable;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
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
