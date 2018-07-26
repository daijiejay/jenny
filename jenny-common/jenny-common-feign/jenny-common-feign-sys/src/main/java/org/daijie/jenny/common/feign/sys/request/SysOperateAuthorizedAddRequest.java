package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;

public class SysOperateAuthorizedAddRequest {

    /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号", required = true)
    private Integer menuId;

    /**
     * 操作功能类型：表格
     */
	@ApiModelProperty(value = "操作功能类型", required = true)
    private String operateType;

    /**
     * 操作功能编号
     */
	@ApiModelProperty(value = "操作功能编号", required = true)
    private Integer operateId;

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
