package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;

public class SysOperateAuthorizedRequest {

	/**
     * 操作授权编号
     */
	@ApiModelProperty(value = "操作授权编号", required = true)
    private Integer operateAuthorizedId;

    /**
     * 角色编号
     */
	@ApiModelProperty(value = "角色编号")
    private Integer roleId;

    /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号")
    private Integer menuId;

    /**
     * 操作功能类型：表格
     */
	@ApiModelProperty(value = "操作功能类型")
    private String operateType;

    /**
     * 操作功能编号
     */
	@ApiModelProperty(value = "操作功能编号")
    private Integer operateId;

	public Integer getOperateAuthorizedId() {
		return operateAuthorizedId;
	}

	public void setOperateAuthorizedId(Integer operateAuthorizedId) {
		this.operateAuthorizedId = operateAuthorizedId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}
}
