package org.daijie.jenny.common.feign.sys.response;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuSelectedResponse extends SysMenuTreeResponse {

    /**
     * 是否选中
     */
	@ApiModelProperty(value = "是否选中", required = true)
	private Boolean checked = false;
	
	/**
	 * 是否打开
	 */
	@ApiModelProperty(value = "是否打开", required = true)
	private Boolean open = false;

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
}
