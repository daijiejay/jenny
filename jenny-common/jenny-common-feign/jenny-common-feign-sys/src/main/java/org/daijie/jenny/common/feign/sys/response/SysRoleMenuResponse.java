package org.daijie.jenny.common.feign.sys.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleMenuResponse {

	@ApiModelProperty(value = "用户编号", required = true)
	private Integer userId;

	@ApiModelProperty(value = "1级菜单")
	private List<SysMenuAuthorizedResponse> level1 = new ArrayList<SysMenuAuthorizedResponse>();

	@ApiModelProperty(value = "2级菜单")
	private List<SysMenuAuthorizedResponse> level2 = new ArrayList<SysMenuAuthorizedResponse>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<SysMenuAuthorizedResponse> getLevel1() {
		return level1;
	}

	public void setLevel1(List<SysMenuAuthorizedResponse> level1) {
		this.level1 = level1;
	}

	public List<SysMenuAuthorizedResponse> getLevel2() {
		return level2;
	}

	public void setLevel2(List<SysMenuAuthorizedResponse> level2) {
		this.level2 = level2;
	}
}
