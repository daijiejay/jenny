package org.daijie.jenny.common.feign.sys.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleMenuResponse {

	@ApiModelProperty(value = "角色编号", required = true)
	private String userCode;

	@ApiModelProperty(value = "1级菜单")
	private List<SysMenuAuthorizedResponse> level1 = new ArrayList<SysMenuAuthorizedResponse>();

	@ApiModelProperty(value = "2级菜单")
	private List<SysMenuAuthorizedResponse> level2 = new ArrayList<SysMenuAuthorizedResponse>();

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
