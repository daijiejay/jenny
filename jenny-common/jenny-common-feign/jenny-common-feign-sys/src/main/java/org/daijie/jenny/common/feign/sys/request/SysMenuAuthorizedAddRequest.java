package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;

public class SysMenuAuthorizedAddRequest {

    /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号", required = true)
    private Integer menuId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
