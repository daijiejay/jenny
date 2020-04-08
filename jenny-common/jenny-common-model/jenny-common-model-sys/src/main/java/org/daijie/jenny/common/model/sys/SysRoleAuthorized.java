package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_role_authorized")
public class SysRoleAuthorized implements Serializable {

	/**
	 * 用户角色授权编号	
	 */
	@Id
	@Column(name = "role_authorized_id")
	public Integer roleAuthorizedId;

	/**
	 * 用户编号	
	 */
	@Column(name = "user_id")
	public Integer userId;

	/**
	 * 角色编号	
	 */
	@Column(name = "role_id")
	public Integer roleId;

	/**
	 * 设置用户角色授权编号	
	 * @param roleAuthorizedId 用户角色授权编号	
	 */
	public void setRoleAuthorizedId(Integer roleAuthorizedId) {	
		this.roleAuthorizedId = roleAuthorizedId;	
	}

	/**
	 * 获取用户角色授权编号	
	 * @return role_authorized_id 用户角色授权编号	
	 */
	public Integer getRoleAuthorizedId() {	
		return this.roleAuthorizedId;	
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
	 * 设置角色编号	
	 * @param roleId 角色编号	
	 */
	public void setRoleId(Integer roleId) {	
		this.roleId = roleId;	
	}

	/**
	 * 获取角色编号	
	 * @return role_id 角色编号	
	 */
	public Integer getRoleId() {	
		return this.roleId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.roleAuthorizedId == null) ? 0 : this.roleAuthorizedId.hashCode());
		result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
		result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", roleAuthorizedId=").append(this.roleAuthorizedId);
		sb.append(", userId=").append(this.userId);
		sb.append(", roleId=").append(this.roleId);
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
		SysRoleAuthorized other = (SysRoleAuthorized) that;
		return (this.getRoleAuthorizedId() == null ? other.getRoleAuthorizedId() == null : this.getRoleAuthorizedId().equals(other.getRoleAuthorizedId()))
			&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
			&& (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
	}

}