package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_role")
public class SysRole implements Serializable {

	/**
	 * 角色编号	
	 */
	@Id
	@Column(name = "role_id")
	public Integer roleId;

	/**
	 * 角色名称	
	 */
	@Column(name = "role_name")
	public String roleName;

	/**
	 * 是否禁用	
	 */
	@Column(name = "enable")
	public Boolean enable;

	/**
	 * 角色代码	
	 */
	@Column(name = "role_code")
	public String roleCode;

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

	/**
	 * 设置角色名称	
	 * @param roleName 角色名称	
	 */
	public void setRoleName(String roleName) {	
		this.roleName = roleName;	
	}

	/**
	 * 获取角色名称	
	 * @return role_name 角色名称	
	 */
	public String getRoleName() {	
		return this.roleName;	
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
	 * 设置角色代码	
	 * @param roleCode 角色代码	
	 */
	public void setRoleCode(String roleCode) {	
		this.roleCode = roleCode;	
	}

	/**
	 * 获取角色代码	
	 * @return role_code 角色代码	
	 */
	public String getRoleCode() {	
		return this.roleCode;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
		result = prime * result + ((this.roleName == null) ? 0 : this.roleName.hashCode());
		result = prime * result + ((this.enable == null) ? 0 : this.enable.hashCode());
		result = prime * result + ((this.roleCode == null) ? 0 : this.roleCode.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", roleId=").append(this.roleId);
		sb.append(", roleName=").append(this.roleName);
		sb.append(", enable=").append(this.enable);
		sb.append(", roleCode=").append(this.roleCode);
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
		SysRole other = (SysRole) that;
		return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
			&& (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
			&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
			&& (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()));
	}

}