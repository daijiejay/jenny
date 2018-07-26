package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_menu_authorized")
public class SysMenuAuthorized implements Serializable {
    /**
     * 角色菜单授权编号
     */
    @Id
    @Column(name = "menu_authorized_id")
    private Integer menuAuthorizedId;

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

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色菜单授权编号
     *
     * @return menu_authorized_id - 角色菜单授权编号
     */
    public Integer getMenuAuthorizedId() {
        return menuAuthorizedId;
    }

    public SysMenuAuthorized withMenuAuthorizedId(Integer menuAuthorizedId) {
        this.setMenuAuthorizedId(menuAuthorizedId);
        return this;
    }

    /**
     * 设置角色菜单授权编号
     *
     * @param menuAuthorizedId 角色菜单授权编号
     */
    public void setMenuAuthorizedId(Integer menuAuthorizedId) {
        this.menuAuthorizedId = menuAuthorizedId;
    }

    /**
     * 获取角色编号
     *
     * @return role_id - 角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    public SysMenuAuthorized withRoleId(Integer roleId) {
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

    public SysMenuAuthorized withMenuId(Integer menuId) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuAuthorizedId=").append(menuAuthorizedId);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
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
        SysMenuAuthorized other = (SysMenuAuthorized) that;
        return (this.getMenuAuthorizedId() == null ? other.getMenuAuthorizedId() == null : this.getMenuAuthorizedId().equals(other.getMenuAuthorizedId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuAuthorizedId() == null) ? 0 : getMenuAuthorizedId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        return result;
    }
}