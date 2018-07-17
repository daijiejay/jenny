package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_table")
public class SysTable implements Serializable {
    /**
     * 表格编号
     */
    @Id
    @Column(name = "table_id")
    private Integer tableId;

    /**
     * 菜单编号
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 表格名称
     */
    @Column(name = "table_name")
    private String tableName;

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
     * 表格目标元素
     */
    @Column(name = "table_target")
    private String tableTarget;

    /**
     * 表格行主键编号名
     */
    @Column(name = "unique_id")
    private String uniqueId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取表格编号
     *
     * @return table_id - 表格编号
     */
    public Integer getTableId() {
        return tableId;
    }

    public SysTable withTableId(Integer tableId) {
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
     * 获取菜单编号
     *
     * @return menu_id - 菜单编号
     */
    public Integer getMenuId() {
        return menuId;
    }

    public SysTable withMenuId(Integer menuId) {
        this.setMenuId(menuId);
        return this;
    }

    /**
     * 设置菜单编号
     *
     * @param menuId 菜单编号
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取表格名称
     *
     * @return table_name - 表格名称
     */
    public String getTableName() {
        return tableName;
    }

    public SysTable withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * 设置表格名称
     *
     * @param tableName 表格名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 获取接口服务名
     *
     * @return interface_server_id - 接口服务名
     */
    public String getInterfaceServerId() {
        return interfaceServerId;
    }

    public SysTable withInterfaceServerId(String interfaceServerId) {
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

    public SysTable withInterfaceUrl(String interfaceUrl) {
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

    public SysTable withInterfaceMethod(String interfaceMethod) {
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
     * 获取表格目标元素
     *
     * @return table_target - 表格目标元素
     */
    public String getTableTarget() {
        return tableTarget;
    }

    public SysTable withTableTarget(String tableTarget) {
        this.setTableTarget(tableTarget);
        return this;
    }

    /**
     * 设置表格目标元素
     *
     * @param tableTarget 表格目标元素
     */
    public void setTableTarget(String tableTarget) {
        this.tableTarget = tableTarget == null ? null : tableTarget.trim();
    }

    /**
     * 获取表格行主键编号名
     *
     * @return unique_id - 表格行主键编号名
     */
    public String getUniqueId() {
        return uniqueId;
    }

    public SysTable withUniqueId(String uniqueId) {
        this.setUniqueId(uniqueId);
        return this;
    }

    /**
     * 设置表格行主键编号名
     *
     * @param uniqueId 表格行主键编号名
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId == null ? null : uniqueId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tableId=").append(tableId);
        sb.append(", menuId=").append(menuId);
        sb.append(", tableName=").append(tableName);
        sb.append(", interfaceServerId=").append(interfaceServerId);
        sb.append(", interfaceUrl=").append(interfaceUrl);
        sb.append(", interfaceMethod=").append(interfaceMethod);
        sb.append(", tableTarget=").append(tableTarget);
        sb.append(", uniqueId=").append(uniqueId);
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
        SysTable other = (SysTable) that;
        return (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getTableName() == null ? other.getTableName() == null : this.getTableName().equals(other.getTableName()))
            && (this.getInterfaceServerId() == null ? other.getInterfaceServerId() == null : this.getInterfaceServerId().equals(other.getInterfaceServerId()))
            && (this.getInterfaceUrl() == null ? other.getInterfaceUrl() == null : this.getInterfaceUrl().equals(other.getInterfaceUrl()))
            && (this.getInterfaceMethod() == null ? other.getInterfaceMethod() == null : this.getInterfaceMethod().equals(other.getInterfaceMethod()))
            && (this.getTableTarget() == null ? other.getTableTarget() == null : this.getTableTarget().equals(other.getTableTarget()))
            && (this.getUniqueId() == null ? other.getUniqueId() == null : this.getUniqueId().equals(other.getUniqueId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTableId() == null) ? 0 : getTableId().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getTableName() == null) ? 0 : getTableName().hashCode());
        result = prime * result + ((getInterfaceServerId() == null) ? 0 : getInterfaceServerId().hashCode());
        result = prime * result + ((getInterfaceUrl() == null) ? 0 : getInterfaceUrl().hashCode());
        result = prime * result + ((getInterfaceMethod() == null) ? 0 : getInterfaceMethod().hashCode());
        result = prime * result + ((getTableTarget() == null) ? 0 : getTableTarget().hashCode());
        result = prime * result + ((getUniqueId() == null) ? 0 : getUniqueId().hashCode());
        return result;
    }
}