package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysUserLoginRequest implements Serializable {

	@ApiModelProperty(value = "用户名称")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
