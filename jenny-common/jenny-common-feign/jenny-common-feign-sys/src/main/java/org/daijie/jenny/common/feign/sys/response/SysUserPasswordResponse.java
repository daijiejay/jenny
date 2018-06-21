package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysUserPasswordResponse implements Serializable {

	@ApiModelProperty(value = "用户编号", required = true)
	private String userCode;

	@ApiModelProperty(value = "密码", required = true)
	private String password;

	@ApiModelProperty(value = "加密盐", required = true)
    private String salt;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
