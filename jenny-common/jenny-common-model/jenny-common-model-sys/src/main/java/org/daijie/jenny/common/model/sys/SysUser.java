package org.daijie.jenny.common.model.sys;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_user")
public class SysUser implements Serializable {

	/**
	 * 用户编号	
	 */
	@Id
	@Column(name = "user_id")
	public Integer userId;

	/**
	 * 头像	
	 */
	@Column(name = "portrait")
	public String portrait;

	/**
	 * 用户名	
	 */
	@Column(name = "user_name")
	public String userName;

	/**
	 * 是否禁用	
	 */
	@Column(name = "enable")
	public Boolean enable;

	/**
	 * 邮箱	
	 */
	@Column(name = "email")
	public String email;

	/**
	 * 加密盐	
	 */
	@Column(name = "salt")
	public String salt;

	/**
	 * 密码	
	 */
	@Column(name = "password")
	public String password;

	/**
	 * 是否删除	
	 */
	@Column(name = "cancel")
	public Boolean cancel;

	/**
	 * 性别	
	 */
	@Column(name = "gender")
	public Boolean gender;

	/**
	 * 创建时间	
	 */
	@Column(name = "create_time")
	public Date createTime;

	/**
	 * 是否管理员用户	
	 */
	@Column(name = "admin")
	public Boolean admin;

	/**
	 * 生日	
	 */
	@Column(name = "birthday")
	public Date birthday;

	/**
	 * 手机号	
	 */
	@Column(name = "mobile")
	public String mobile;

	/**
	 * 设置头像	
	 * @param portrait 头像	
	 */
	public void setPortrait(String portrait) {	
		this.portrait = portrait;	
	}

	/**
	 * 获取头像	
	 * @return portrait 头像	
	 */
	public String getPortrait() {	
		return this.portrait;	
	}

	/**
	 * 设置用户编号	
	 * @param userId 用户编号	
	 */
	public void setUserId(Integer userId) {	
		this.userId = userId;	
	}

	/**
	 * 获取用户编号	
	 * @return user_id 用户编号	
	 */
	public Integer getUserId() {	
		return this.userId;	
	}

	/**
	 * 设置用户名	
	 * @param userName 用户名	
	 */
	public void setUserName(String userName) {	
		this.userName = userName;	
	}

	/**
	 * 获取用户名	
	 * @return user_name 用户名	
	 */
	public String getUserName() {	
		return this.userName;	
	}

	/**
	 * 设置是否禁用	
	 * @param enable 是否禁用	
	 */
	public void setEnable(Boolean enable) {	
		this.enable = enable;	
	}

	/**
	 * 获取是否禁用	
	 * @return enable 是否禁用	
	 */
	public Boolean getEnable() {	
		return this.enable;	
	}

	/**
	 * 设置邮箱	
	 * @param email 邮箱	
	 */
	public void setEmail(String email) {	
		this.email = email;	
	}

	/**
	 * 获取邮箱	
	 * @return email 邮箱	
	 */
	public String getEmail() {	
		return this.email;	
	}

	/**
	 * 设置加密盐	
	 * @param salt 加密盐	
	 */
	public void setSalt(String salt) {	
		this.salt = salt;	
	}

	/**
	 * 获取加密盐	
	 * @return salt 加密盐	
	 */
	public String getSalt() {	
		return this.salt;	
	}

	/**
	 * 设置密码	
	 * @param password 密码	
	 */
	public void setPassword(String password) {	
		this.password = password;	
	}

	/**
	 * 获取密码	
	 * @return password 密码	
	 */
	public String getPassword() {	
		return this.password;	
	}

	/**
	 * 设置是否删除	
	 * @param cancel 是否删除	
	 */
	public void setCancel(Boolean cancel) {	
		this.cancel = cancel;	
	}

	/**
	 * 获取是否删除	
	 * @return cancel 是否删除	
	 */
	public Boolean getCancel() {	
		return this.cancel;	
	}

	/**
	 * 设置性别	
	 * @param gender 性别	
	 */
	public void setGender(Boolean gender) {	
		this.gender = gender;	
	}

	/**
	 * 获取性别	
	 * @return gender 性别	
	 */
	public Boolean getGender() {	
		return this.gender;	
	}

	/**
	 * 设置创建时间	
	 * @param createTime 创建时间	
	 */
	public void setCreateTime(Date createTime) {	
		this.createTime = createTime;	
	}

	/**
	 * 获取创建时间	
	 * @return create_time 创建时间	
	 */
	public Date getCreateTime() {	
		return this.createTime;	
	}

	/**
	 * 设置是否管理员用户	
	 * @param admin 是否管理员用户	
	 */
	public void setAdmin(Boolean admin) {	
		this.admin = admin;	
	}

	/**
	 * 获取是否管理员用户	
	 * @return admin 是否管理员用户	
	 */
	public Boolean getAdmin() {	
		return this.admin;	
	}

	/**
	 * 设置生日	
	 * @param birthday 生日	
	 */
	public void setBirthday(Date birthday) {	
		this.birthday = birthday;	
	}

	/**
	 * 获取生日	
	 * @return birthday 生日	
	 */
	public Date getBirthday() {	
		return this.birthday;	
	}

	/**
	 * 设置手机号	
	 * @param mobile 手机号	
	 */
	public void setMobile(String mobile) {	
		this.mobile = mobile;	
	}

	/**
	 * 获取手机号	
	 * @return mobile 手机号	
	 */
	public String getMobile() {	
		return this.mobile;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
		result = prime * result + ((this.portrait == null) ? 0 : this.portrait.hashCode());
		result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
		result = prime * result + ((this.enable == null) ? 0 : this.enable.hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.salt == null) ? 0 : this.salt.hashCode());
		result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result + ((this.cancel == null) ? 0 : this.cancel.hashCode());
		result = prime * result + ((this.gender == null) ? 0 : this.gender.hashCode());
		result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
		result = prime * result + ((this.admin == null) ? 0 : this.admin.hashCode());
		result = prime * result + ((this.birthday == null) ? 0 : this.birthday.hashCode());
		result = prime * result + ((this.mobile == null) ? 0 : this.mobile.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", userId=").append(this.userId);
		sb.append(", portrait=").append(this.portrait);
		sb.append(", userName=").append(this.userName);
		sb.append(", enable=").append(this.enable);
		sb.append(", email=").append(this.email);
		sb.append(", salt=").append(this.salt);
		sb.append(", password=").append(this.password);
		sb.append(", cancel=").append(this.cancel);
		sb.append(", gender=").append(this.gender);
		sb.append(", createTime=").append(this.createTime);
		sb.append(", admin=").append(this.admin);
		sb.append(", birthday=").append(this.birthday);
		sb.append(", mobile=").append(this.mobile);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {	
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysUser other = (SysUser) that;
		return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
			&& (this.getPortrait() == null ? other.getPortrait() == null : this.getPortrait().equals(other.getPortrait()))
			&& (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
			&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
			&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
			&& (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
			&& (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
			&& (this.getCancel() == null ? other.getCancel() == null : this.getCancel().equals(other.getCancel()))
			&& (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
			&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
			&& (this.getAdmin() == null ? other.getAdmin() == null : this.getAdmin().equals(other.getAdmin()))
			&& (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
			&& (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()));
	}

}