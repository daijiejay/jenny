package org.daijie.jenny.common.feign.sys.response;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleSelectedResponse extends SysRoleResponse {

    /**
     * 是否选中
     */
	@ApiModelProperty(value = "是否选中", required = true)
	private Boolean isSelected = false;

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
