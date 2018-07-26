package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_operate_authorized")
public class SysOperateAuthorized implements Serializable {
    /**
     * 操作授权编号
     */
    @Id
    @Column(name = "operate_authorized_id")
    private Integer operateAuthorizedId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单编号
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 操作功能类型：表格
     */
    @Column(name = "operate_type")
    private String operateType;

    /**
     * 操作功能编号
     */
    @Column(name = "operate_id")
    private Integer operateId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取操作授权编号
     *
     * @return operate_authorized_id - 操作授权编号
     */
    public Integer getOperateAuthorizedId() {
        return operateAuthorizedId;
    }

    public SysOperateAuthorized withOperateAuthorizedId(Integer operateAuthorizedId) {
        this.setOperateAuthorizedId(operateAuthorizedId);
        return this;
    }

    /**
     * 设置操作授权编号
     *
     * @param operateAuthorizedId 操作授权编号
     */
    public void setOperateAuthorizedId(Integer operateAuthorizedId) {
        this.operateAuthorizedId = operateAuthorizedId;
    }

    /**
     * 获取角色编号
     *
     * @return role_id - 角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    public SysOperateAuthorized withRoleId(Integer roleId) {
        this.setRoleId(roleId);
        return this;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单编号
     *
     * @return menu_id - 菜单编号
     */
    public Integer getMenuId() {
        return menuId;
    }

    public SysOperateAuthorized withMenuId(Integer menuId) {
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
     * 获取操作功能类型：表格
     *
     * @return operate_type - 操作功能类型：表格
     */
    public String getOperateType() {
        return operateType;
    }

    public SysOperateAuthorized withOperateType(String operateType) {
        this.setOperateType(operateType);
        return this;
    }

    /**
     * 设置操作功能类型：表格
     *
     * @param operateType 操作功能类型：表格
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     * 获取操作功能编号
     *
     * @return operate_id - 操作功能编号
     */
    public Integer getOperateId() {
        return operateId;
    }

    public SysOperateAuthorized withOperateId(Integer operateId) {
        this.setOperateId(operateId);
        return this;
    }

    /**
     * 设置操作功能编号
     *
     * @param operateId 操作功能编号
     */
    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operateAuthorizedId=").append(operateAuthorizedId);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateId=").append(operateId);
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
        SysOperateAuthorized other = (SysOperateAuthorized) that;
        return (this.getOperateAuthorizedId() == null ? other.getOperateAuthorizedId() == null : this.getOperateAuthorizedId().equals(other.getOperateAuthorizedId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getOperateId() == null ? other.getOperateId() == null : this.getOperateId().equals(other.getOperateId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOperateAuthorizedId() == null) ? 0 : getOperateAuthorizedId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getOperateId() == null) ? 0 : getOperateId().hashCode());
        return result;
    }
}