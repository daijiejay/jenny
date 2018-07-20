package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleSelectedPageRequest extends SysRolePageRequest {
    /**
     * 用户编号
     */
	@ApiModelProperty(value = "用户编号", required = true)
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
