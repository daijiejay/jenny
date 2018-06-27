package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysUserUpdateRequest implements Serializable {
	
	@ApiModelProperty(value = "用户ID")
	private Integer id;

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "用户名称")
	private String userName;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
	@ApiModelProperty(value = "加密盐")
	private String salt;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生日")
	private Date birthday;

	@ApiModelProperty(value = "姓别")
	private Boolean gender;

	@ApiModelProperty(value = "头像")
	private String portrait;

	@ApiModelProperty(value = "是否禁用")
	private Boolean enable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
