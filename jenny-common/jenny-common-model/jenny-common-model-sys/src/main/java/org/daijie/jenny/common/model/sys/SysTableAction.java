package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_table_action")
public class SysTableAction implements Serializable {

	/**
	 * 功能编号	
	 */
	@Id
	@Column(name = "action_id")
	public Integer actionId;

	/**
	 * 接口方式	
	 */
	@Column(name = "interface_method")
	public String interfaceMethod;

	/**
	 * 表单目标元素	
	 */
	@Column(name = "form_target")
	public String formTarget;

	/**
	 * 展示排序	
	 */
	@Column(name = "show_sort")
	public Integer showSort;

	/**
	 * 交互类型：确认,表单,扩展	
	 */
	@Column(name = "mutual_type")
	public String mutualType;

	/**
	 * 接口地址	
	 */
	@Column(name = "interface_url")
	public String interfaceUrl;

	/**
	 * 表格编号	
	 */
	@Column(name = "table_id")
	public Integer tableId;

	/**
	 * 图标	
	 */
	@Column(name = "icon")
	public String icon;

	/**
	 * 功能名称	
	 */
	@Column(name = "action_name")
	public String actionName;

	/**
	 * 接口服务名	
	 */
	@Column(name = "interface_server_id")
	public String interfaceServerId;

	/**
	 * 功能类型：列表单行操作,列表工具栏	
	 */
	@Column(name = "action_type")
	public String actionType;

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
	 * 设置表单目标元素	
	 * @param formTarget 表单目标元素	
	 */
	public void setFormTarget(String formTarget) {	
		this.formTarget = formTarget;	
	}

	/**
	 * 获取表单目标元素	
	 * @return form_target 表单目标元素	
	 */
	public String getFormTarget() {	
		return this.formTarget;	
	}

	/**
	 * 设置展示排序	
	 * @param showSort 展示排序	
	 */
	public void setShowSort(Integer showSort) {	
		this.showSort = showSort;	
	}

	/**
	 * 获取展示排序	
	 * @return show_sort 展示排序	
	 */
	public Integer getShowSort() {	
		return this.showSort;	
	}

	/**
	 * 设置交互类型：确认,表单,扩展	
	 * @param mutualType	
	 */
	public void setMutualType(String mutualType) {	
		this.mutualType = mutualType;	
	}

	/**
	 * 获取交互类型：确认,表单,扩展	
	 * @return mutual_type 交互类型：确认	
	 */
	public String getMutualType() {	
		return this.mutualType;	
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
	 * 设置图标	
	 * @param icon 图标	
	 */
	public void setIcon(String icon) {	
		this.icon = icon;	
	}

	/**
	 * 获取图标	
	 * @return icon 图标	
	 */
	public String getIcon() {	
		return this.icon;	
	}

	/**
	 * 设置功能名称	
	 * @param actionName 功能名称	
	 */
	public void setActionName(String actionName) {	
		this.actionName = actionName;	
	}

	/**
	 * 获取功能名称	
	 * @return action_name 功能名称	
	 */
	public String getActionName() {	
		return this.actionName;	
	}

	/**
	 * 设置功能编号	
	 * @param actionId 功能编号	
	 */
	public void setActionId(Integer actionId) {	
		this.actionId = actionId;	
	}

	/**
	 * 获取功能编号	
	 * @return action_id 功能编号	
	 */
	public Integer getActionId() {	
		return this.actionId;	
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

	/**
	 * 设置功能类型：列表单行操作,列表工具栏	
	 * @param actionType	
	 */
	public void setActionType(String actionType) {	
		this.actionType = actionType;	
	}

	/**
	 * 获取功能类型：列表单行操作,列表工具栏	
	 * @return action_type 功能类型：列表单行操作	
	 */
	public String getActionType() {	
		return this.actionType;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.actionId == null) ? 0 : this.actionId.hashCode());
		result = prime * result + ((this.interfaceMethod == null) ? 0 : this.interfaceMethod.hashCode());
		result = prime * result + ((this.formTarget == null) ? 0 : this.formTarget.hashCode());
		result = prime * result + ((this.showSort == null) ? 0 : this.showSort.hashCode());
		result = prime * result + ((this.mutualType == null) ? 0 : this.mutualType.hashCode());
		result = prime * result + ((this.interfaceUrl == null) ? 0 : this.interfaceUrl.hashCode());
		result = prime * result + ((this.tableId == null) ? 0 : this.tableId.hashCode());
		result = prime * result + ((this.icon == null) ? 0 : this.icon.hashCode());
		result = prime * result + ((this.actionName == null) ? 0 : this.actionName.hashCode());
		result = prime * result + ((this.interfaceServerId == null) ? 0 : this.interfaceServerId.hashCode());
		result = prime * result + ((this.actionType == null) ? 0 : this.actionType.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", actionId=").append(this.actionId);
		sb.append(", interfaceMethod=").append(this.interfaceMethod);
		sb.append(", formTarget=").append(this.formTarget);
		sb.append(", showSort=").append(this.showSort);
		sb.append(", mutualType=").append(this.mutualType);
		sb.append(", interfaceUrl=").append(this.interfaceUrl);
		sb.append(", tableId=").append(this.tableId);
		sb.append(", icon=").append(this.icon);
		sb.append(", actionName=").append(this.actionName);
		sb.append(", interfaceServerId=").append(this.interfaceServerId);
		sb.append(", actionType=").append(this.actionType);
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
		SysTableAction other = (SysTableAction) that;
		return (this.getActionId() == null ? other.getActionId() == null : this.getActionId().equals(other.getActionId()))
			&& (this.getInterfaceMethod() == null ? other.getInterfaceMethod() == null : this.getInterfaceMethod().equals(other.getInterfaceMethod()))
			&& (this.getFormTarget() == null ? other.getFormTarget() == null : this.getFormTarget().equals(other.getFormTarget()))
			&& (this.getShowSort() == null ? other.getShowSort() == null : this.getShowSort().equals(other.getShowSort()))
			&& (this.getMutualType() == null ? other.getMutualType() == null : this.getMutualType().equals(other.getMutualType()))
			&& (this.getInterfaceUrl() == null ? other.getInterfaceUrl() == null : this.getInterfaceUrl().equals(other.getInterfaceUrl()))
			&& (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
			&& (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
			&& (this.getActionName() == null ? other.getActionName() == null : this.getActionName().equals(other.getActionName()))
			&& (this.getInterfaceServerId() == null ? other.getInterfaceServerId() == null : this.getInterfaceServerId().equals(other.getInterfaceServerId()))
			&& (this.getActionType() == null ? other.getActionType() == null : this.getActionType().equals(other.getActionType()));
	}

}