package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_menu_authorized")
public class SysMenuAuthorized implements Serializable {

	/**
	 * 角色菜单授权编号	
	 */
	@Id
	@Column(name = "menu_authorized_id")
	public Integer menuAuthorizedId;

	/**
	 * 角色编号	
	 */
	@Column(name = "role_id")
	public Integer roleId;

	/**
	 * 菜单编号	
	 */
	@Column(name = "menu_id")
	public Integer menuId;

	/**
	 * 设置角色菜单授权编号	
	 * @param menuAuthorizedId 角色菜单授权编号	
	 */
	public void setMenuAuthorizedId(Integer menuAuthorizedId) {	
		this.menuAuthorizedId = menuAuthorizedId;	
	}

	/**
	 * 获取角色菜单授权编号	
	 * @return menu_authorized_id 角色菜单授权编号	
	 */
	public Integer getMenuAuthorizedId() {	
		return this.menuAuthorizedId;	
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

	/**
	 * 设置菜单编号	
	 * @param menuId 菜单编号	
	 */
	public void setMenuId(Integer menuId) {	
		this.menuId = menuId;	
	}

	/**
	 * 获取菜单编号	
	 * @return menu_id 菜单编号	
	 */
	public Integer getMenuId() {	
		return this.menuId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.menuAuthorizedId == null) ? 0 : this.menuAuthorizedId.hashCode());
		result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
		result = prime * result + ((this.menuId == null) ? 0 : this.menuId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", menuAuthorizedId=").append(this.menuAuthorizedId);
		sb.append(", roleId=").append(this.roleId);
		sb.append(", menuId=").append(this.menuId);
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
		SysMenuAuthorized other = (SysMenuAuthorized) that;
		return (this.getMenuAuthorizedId() == null ? other.getMenuAuthorizedId() == null : this.getMenuAuthorizedId().equals(other.getMenuAuthorizedId()))
			&& (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
			&& (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()));
	}

}