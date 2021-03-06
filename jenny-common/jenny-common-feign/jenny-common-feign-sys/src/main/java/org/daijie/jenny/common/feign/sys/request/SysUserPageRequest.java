package org.daijie.jenny.common.feign.sys.request;

import java.util.Date;

import org.daijie.jdbc.mybatis.example.ExampleExecutePage;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.jenny.common.model.sys.SysUser;

import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example.Criteria;

@SuppressWarnings("serial")
public class SysUserPageRequest extends ExampleExecutePage<SysUser, SysUserResponse> {

	@ApiModelProperty(value = "用户编号")
	private Integer userId;

	@ApiModelProperty(value = "用户名称")
	private String userName;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "生日开始日期")
	private Date birthdayStart;
	
	@ApiModelProperty(value = "生日线束日期")
	private Date birthdayEnd;

	@ApiModelProperty(value = "姓别")
	private Boolean gender;

	@ApiModelProperty(value = "头像")
	private String portrait;

	@ApiModelProperty(value = "创建时间开始时间")
	private Date createTimeStart;
	
	@ApiModelProperty(value = "创建时间结束时间")
	private Date createTimeEnd;

	@ApiModelProperty(value = "是否禁用")
	private Boolean enable;
	
	@ApiModelProperty(value = "是否删除")
	private Boolean cancel;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getBirthdayStart() {
		return birthdayStart;
	}

	public void setBirthdayStart(Date birthdayStart) {
		this.birthdayStart = birthdayStart;
	}

	public Date getBirthdayEnd() {
		return birthdayEnd;
	}

	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
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

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}
	
	@Override
	public void extendConditions(Criteria example) {
		example.andEqualTo("admin", false);
	}
}
