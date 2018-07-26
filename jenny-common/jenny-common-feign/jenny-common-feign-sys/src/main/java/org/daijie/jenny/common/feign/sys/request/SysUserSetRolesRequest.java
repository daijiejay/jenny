package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysUserSetRolesRequest implements Serializable {
	
	@ApiModelProperty(value = "用户编号", required = true)
	private Integer userId;

	@ApiModelProperty(value = "角色编号数组", required = true)
	private List<Integer> roleIds = new ArrayList<Integer>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
}
