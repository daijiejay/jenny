package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_table")
public class SysTable implements Serializable {

	/**
	 * 表格编号	
	 */
	@Id
	@Column(name = "table_id")
	public Integer tableId;

	/**
	 * 表格名称	
	 */
	@Column(name = "table_name")
	public String tableName;

	/**
	 * 接口方式	
	 */
	@Column(name = "interface_method")
	public String interfaceMethod;

	/**
	 * 菜单编号	
	 */
	@Column(name = "menu_id")
	public Integer menuId;

	/**
	 * 接口地址	
	 */
	@Column(name = "interface_url")
	public String interfaceUrl;

	/**
	 * 表格行主键编号名	
	 */
	@Column(name = "unique_id")
	public String uniqueId;

	/**
	 * 表格目标元素	
	 */
	@Column(name = "table_target")
	public String tableTarget;

	/**
	 * 接口服务名	
	 */
	@Column(name = "interface_server_id")
	public String interfaceServerId;

	/**
	 * 设置表格名称	
	 * @param tableName 表格名称	
	 */
	public void setTableName(String tableName) {	
		this.tableName = tableName;	
	}

	/**
	 * 获取表格名称	
	 * @return table_name 表格名称	
	 */
	public String getTableName() {	
		return this.tableName;	
	}

	/**
	 * 设置接口方式	
	 * @param interfaceMethod 接口方式	
	 */
	public void setInterfaceMethod(String interfaceMethod) {	
		this.interfaceMethod = interfaceMethod;	
	}

	/**
	 * 获取接口方式	
	 * @return interface_method 接口方式	
	 */
	public String getInterfaceMethod() {	
		return this.interfaceMethod;	
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
	 * 设置接口地址	
	 * @param interfaceUrl 接口地址	
	 */
	public void setInterfaceUrl(String interfaceUrl) {	
		this.interfaceUrl = interfaceUrl;	
	}

	/**
	 * 获取接口地址	
	 * @return interface_url 接口地址	
	 */
	public String getInterfaceUrl() {	
		return this.interfaceUrl;	
	}

	/**
	 * 设置表格编号	
	 * @param tableId 表格编号	
	 */
	public void setTableId(Integer tableId) {	
		this.tableId = tableId;	
	}

	/**
	 * 获取表格编号	
	 * @return table_id 表格编号	
	 */
	public Integer getTableId() {	
		return this.tableId;	
	}

	/**
	 * 设置表格行主键编号名	
	 * @param uniqueId 表格行主键编号名	
	 */
	public void setUniqueId(String uniqueId) {	
		this.uniqueId = uniqueId;	
	}

	/**
	 * 获取表格行主键编号名	
	 * @return unique_id 表格行主键编号名	
	 */
	public String getUniqueId() {	
		return this.uniqueId;	
	}

	/**
	 * 设置表格目标元素	
	 * @param tableTarget 表格目标元素	
	 */
	public void setTableTarget(String tableTarget) {	
		this.tableTarget = tableTarget;	
	}

	/**
	 * 获取表格目标元素	
	 * @return table_target 表格目标元素	
	 */
	public String getTableTarget() {	
		return this.tableTarget;	
	}

	/**
	 * 设置接口服务名	
	 * @param interfaceServerId 接口服务名	
	 */
	public void setInterfaceServerId(String interfaceServerId) {	
		this.interfaceServerId = interfaceServerId;	
	}

	/**
	 * 获取接口服务名	
	 * @return interface_server_id 接口服务名	
	 */
	public String getInterfaceServerId() {	
		return this.interfaceServerId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.tableId == null) ? 0 : this.tableId.hashCode());
		result = prime * result + ((this.tableName == null) ? 0 : this.tableName.hashCode());
		result = prime * result + ((this.interfaceMethod == null) ? 0 : this.interfaceMethod.hashCode());
		result = prime * result + ((this.menuId == null) ? 0 : this.menuId.hashCode());
		result = prime * result + ((this.interfaceUrl == null) ? 0 : this.interfaceUrl.hashCode());
		result = prime * result + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode());
		result = prime * result + ((this.tableTarget == null) ? 0 : this.tableTarget.hashCode());
		result = prime * result + ((this.interfaceServerId == null) ? 0 : this.interfaceServerId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", tableId=").append(this.tableId);
		sb.append(", tableName=").append(this.tableName);
		sb.append(", interfaceMethod=").append(this.interfaceMethod);
		sb.append(", menuId=").append(this.menuId);
		sb.append(", interfaceUrl=").append(this.interfaceUrl);
		sb.append(", uniqueId=").append(this.uniqueId);
		sb.append(", tableTarget=").append(this.tableTarget);
		sb.append(", interfaceServerId=").append(this.interfaceServerId);
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
		SysTable other = (SysTable) that;
		return (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
			&& (this.getTableName() == null ? other.getTableName() == null : this.getTableName().equals(other.getTableName()))
			&& (this.getInterfaceMethod() == null ? other.getInterfaceMethod() == null : this.getInterfaceMethod().equals(other.getInterfaceMethod()))
			&& (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
			&& (this.getInterfaceUrl() == null ? other.getInterfaceUrl() == null : this.getInterfaceUrl().equals(other.getInterfaceUrl()))
			&& (this.getUniqueId() == null ? other.getUniqueId() == null : this.getUniqueId().equals(other.getUniqueId()))
			&& (this.getTableTarget() == null ? other.getTableTarget() == null : this.getTableTarget().equals(other.getTableTarget()))
			&& (this.getInterfaceServerId() == null ? other.getInterfaceServerId() == null : this.getInterfaceServerId().equals(other.getInterfaceServerId()));
	}

}