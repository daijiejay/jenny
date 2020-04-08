package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_operate_authorized")
public class SysOperateAuthorized implements Serializable {

	/**
	 * 操作授权编号	
	 */
	@Id
	@Column(name = "operate_authorized_id")
	public Integer operateAuthorizedId;

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
	 * 操作功能类型：表格	
	 */
	@Column(name = "operate_type")
	public String operateType;

	/**
	 * 操作功能编号	
	 */
	@Column(name = "operate_id")
	public Integer operateId;

	/**
	 * 设置操作授权编号	
	 * @param operateAuthorizedId 操作授权编号	
	 */
	public void setOperateAuthorizedId(Integer operateAuthorizedId) {	
		this.operateAuthorizedId = operateAuthorizedId;	
	}

	/**
	 * 获取操作授权编号	
	 * @return operate_authorized_id 操作授权编号	
	 */
	public Integer getOperateAuthorizedId() {	
		return this.operateAuthorizedId;	
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

	/**
	 * 设置操作功能类型：表格	
	 * @param operateType 操作功能类型：表格	
	 */
	public void setOperateType(String operateType) {	
		this.operateType = operateType;	
	}

	/**
	 * 获取操作功能类型：表格	
	 * @return operate_type 操作功能类型：表格	
	 */
	public String getOperateType() {	
		return this.operateType;	
	}

	/**
	 * 设置操作功能编号	
	 * @param operateId 操作功能编号	
	 */
	public void setOperateId(Integer operateId) {	
		this.operateId = operateId;	
	}

	/**
	 * 获取操作功能编号	
	 * @return operate_id 操作功能编号	
	 */
	public Integer getOperateId() {	
		return this.operateId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.operateAuthorizedId == null) ? 0 : this.operateAuthorizedId.hashCode());
		result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
		result = prime * result + ((this.menuId == null) ? 0 : this.menuId.hashCode());
		result = prime * result + ((this.operateType == null) ? 0 : this.operateType.hashCode());
		result = prime * result + ((this.operateId == null) ? 0 : this.operateId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", operateAuthorizedId=").append(this.operateAuthorizedId);
		sb.append(", roleId=").append(this.roleId);
		sb.append(", menuId=").append(this.menuId);
		sb.append(", operateType=").append(this.operateType);
		sb.append(", operateId=").append(this.operateId);
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
		SysOperateAuthorized other = (SysOperateAuthorized) that;
		return (this.getOperateAuthorizedId() == null ? other.getOperateAuthorizedId() == null : this.getOperateAuthorizedId().equals(other.getOperateAuthorizedId()))
			&& (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
			&& (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
			&& (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
			&& (this.getOperateId() == null ? other.getOperateId() == null : this.getOperateId().equals(other.getOperateId()));
	}

}