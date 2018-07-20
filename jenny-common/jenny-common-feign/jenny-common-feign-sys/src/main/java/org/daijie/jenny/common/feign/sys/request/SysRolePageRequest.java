package org.daijie.jenny.common.feign.sys.request;

import org.daijie.core.result.Page;
import org.daijie.jdbc.mybatis.example.ExampleConditions;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRolePageRequest extends Page implements ExampleConditions {
    /**
     * 角色编号
     */
	@ApiModelProperty(value = "角色编号")
    private Integer roleId;

	/**
     * 角色代码
     */
	@ApiModelProperty(value = "角色代码")
    private Integer roleCode;

    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 是否禁用
     */
	@ApiModelProperty(value = "角色代码")
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
