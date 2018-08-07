package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_table_action")
public class SysTableAction implements Serializable {
    /**
     * 功能编号
     */
    @Id
    @Column(name = "action_id")
    private Integer actionId;

    /**
     * 表格编号
     */
    @Column(name = "table_id")
    private Integer tableId;

    /**
     * 功能名称
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * 功能类型：列表单行操作,列表工具栏
     */
    @Column(name = "action_type")
    private String actionType;

    /**
     * 交互类型：确认,表单,扩展
     */
    @Column(name = "mutual_type")
    private String mutualType;

    /**
     * 接口服务名
     */
    @Column(name = "interface_server_id")
    private String interfaceServerId;

    /**
     * 接口地址
     */
    @Column(name = "interface_url")
    private String interfaceUrl;

    /**
     * 接口方式
     */
    @Column(name = "interface_method")
    private String interfaceMethod;

    /**
     * 表单目标元素
     */
    @Column(name = "form_target")
    private String formTarget;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    private static final long serialVersionUID = 1L;

    /**
     * 获取功能编号
     *
     * @return action_id - 功能编号
     */
    public Integer getActionId() {
        return actionId;
    }

    public SysTableAction withActionId(Integer actionId) {
        this.setActionId(actionId);
        return this;
    }

    /**
     * 设置功能编号
     *
     * @param actionId 功能编号
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    /**
     * 获取表格编号
     *
     * @return table_id - 表格编号
     */
    public Integer getTableId() {
        return tableId;
    }

    public SysTableAction withTableId(Integer tableId) {
        this.setTableId(tableId);
        return this;
    }

    /**
     * 设置表格编号
     *
     * @param tableId 表格编号
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    /**
     * 获取功能名称
     *
     * @return action_name - 功能名称
     */
    public String getActionName() {
        return actionName;
    }

    public SysTableAction withActionName(String actionName) {
        this.setActionName(actionName);
        return this;
    }

    /**
     * 设置功能名称
     *
     * @param actionName 功能名称
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    /**
     * 获取功能类型：列表单行操作,列表工具栏
     *
     * @return action_type - 功能类型：列表单行操作,列表工具栏
     */
    public String getActionType() {
        return actionType;
    }

    public SysTableAction withActionType(String actionType) {
        this.setActionType(actionType);
        return this;
    }

    /**
     * 设置功能类型：列表单行操作,列表工具栏
     *
     * @param actionType 功能类型：列表单行操作,列表工具栏
     */
    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    /**
     * 获取交互类型：确认,表单,扩展
     *
     * @return mutual_type - 交互类型：确认,表单,扩展
     */
    public String getMutualType() {
        return mutualType;
    }

    public SysTableAction withMutualType(String mutualType) {
        this.setMutualType(mutualType);
        return this;
    }

    /**
     * 设置交互类型：确认,表单,扩展
     *
     * @param mutualType 交互类型：确认,表单,扩展
     */
    public void setMutualType(String mutualType) {
        this.mutualType = mutualType == null ? null : mutualType.trim();
    }

    /**
     * 获取接口服务名
     *
     * @return interface_server_id - 接口服务名
     */
    public String getInterfaceServerId() {
        return interfaceServerId;
    }

    public SysTableAction withInterfaceServerId(String interfaceServerId) {
        this.setInterfaceServerId(interfaceServerId);
        return this;
    }

    /**
     * 设置接口服务名
     *
     * @param interfaceServerId 接口服务名
     */
    public void setInterfaceServerId(String interfaceServerId) {
        this.interfaceServerId = interfaceServerId == null ? null : interfaceServerId.trim();
    }

    /**
     * 获取接口地址
     *
     * @return interface_url - 接口地址
     */
    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public SysTableAction withInterfaceUrl(String interfaceUrl) {
        this.setInterfaceUrl(interfaceUrl);
        return this;
    }

    /**
     * 设置接口地址
     *
     * @param interfaceUrl 接口地址
     */
    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl == null ? null : interfaceUrl.trim();
    }

    /**
     * 获取接口方式
     *
     * @return interface_method - 接口方式
     */
    public String getInterfaceMethod() {
        return interfaceMethod;
    }

    public SysTableAction withInterfaceMethod(String interfaceMethod) {
        this.setInterfaceMethod(interfaceMethod);
        return this;
    }

    /**
     * 设置接口方式
     *
     * @param interfaceMethod 接口方式
     */
    public void setInterfaceMethod(String interfaceMethod) {
        this.interfaceMethod = interfaceMethod == null ? null : interfaceMethod.trim();
    }

    /**
     * 获取表单目标元素
     *
     * @return form_target - 表单目标元素
     */
    public String getFormTarget() {
        return formTarget;
    }

    public SysTableAction withFormTarget(String formTarget) {
        this.setFormTarget(formTarget);
        return this;
    }

    /**
     * 设置表单目标元素
     *
     * @param formTarget 表单目标元素
     */
    public void setFormTarget(String formTarget) {
        this.formTarget = formTarget == null ? null : formTarget.trim();
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    public SysTableAction withIcon(String icon) {
        this.setIcon(icon);
        return this;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", actionId=").append(actionId);
        sb.append(", tableId=").append(tableId);
        sb.append(", actionName=").append(actionName);
        sb.append(", actionType=").append(actionType);
        sb.append(", mutualType=").append(mutualType);
        sb.append(", interfaceServerId=").append(interfaceServerId);
        sb.append(", interfaceUrl=").append(interfaceUrl);
        sb.append(", interfaceMethod=").append(interfaceMethod);
        sb.append(", formTarget=").append(formTarget);
        sb.append(", icon=").append(icon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
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
            && (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
            && (this.getActionName() == null ? other.getActionName() == null : this.getActionName().equals(other.getActionName()))
            && (this.getActionType() == null ? other.getActionType() == null : this.getActionType().equals(other.getActionType()))
            && (this.getMutualType() == null ? other.getMutualType() == null : this.getMutualType().equals(other.getMutualType()))
            && (this.getInterfaceServerId() == null ? other.getInterfaceServerId() == null : this.getInterfaceServerId().equals(other.getInterfaceServerId()))
            && (this.getInterfaceUrl() == null ? other.getInterfaceUrl() == null : this.getInterfaceUrl().equals(other.getInterfaceUrl()))
            && (this.getInterfaceMethod() == null ? other.getInterfaceMethod() == null : this.getInterfaceMethod().equals(other.getInterfaceMethod()))
            && (this.getFormTarget() == null ? other.getFormTarget() == null : this.getFormTarget().equals(other.getFormTarget()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActionId() == null) ? 0 : getActionId().hashCode());
        result = prime * result + ((getTableId() == null) ? 0 : getTableId().hashCode());
        result = prime * result + ((getActionName() == null) ? 0 : getActionName().hashCode());
        result = prime * result + ((getActionType() == null) ? 0 : getActionType().hashCode());
        result = prime * result + ((getMutualType() == null) ? 0 : getMutualType().hashCode());
        result = prime * result + ((getInterfaceServerId() == null) ? 0 : getInterfaceServerId().hashCode());
        result = prime * result + ((getInterfaceUrl() == null) ? 0 : getInterfaceUrl().hashCode());
        result = prime * result + ((getInterfaceMethod() == null) ? 0 : getInterfaceMethod().hashCode());
        result = prime * result + ((getFormTarget() == null) ? 0 : getFormTarget().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        return result;
    }
}