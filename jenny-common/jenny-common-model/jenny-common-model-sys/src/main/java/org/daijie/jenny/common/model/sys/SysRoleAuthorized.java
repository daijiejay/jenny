package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role_authorized")
public class SysRoleAuthorized implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 角色代码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 菜单代码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 授权类型
     */
    @Column(name = "authorized_types")
    private String authorizedTypes;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public SysRoleAuthorized withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色代码
     *
     * @return role_code - 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    public SysRoleAuthorized withRoleCode(String roleCode) {
        this.setRoleCode(roleCode);
        return this;
    }

    /**
     * 设置角色代码
     *
     * @param roleCode 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取菜单代码
     *
     * @return menu_code - 菜单代码
     */
    public String getMenuCode() {
        return menuCode;
    }

    public SysRoleAuthorized withMenuCode(String menuCode) {
        this.setMenuCode(menuCode);
        return this;
    }

    /**
     * 设置菜单代码
     *
     * @param menuCode 菜单代码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取授权类型
     *
     * @return authorized_types - 授权类型
     */
    public String getAuthorizedTypes() {
        return authorizedTypes;
    }

    public SysRoleAuthorized withAuthorizedTypes(String authorizedTypes) {
        this.setAuthorizedTypes(authorizedTypes);
        return this;
    }

    /**
     * 设置授权类型
     *
     * @param authorizedTypes 授权类型
     */
    public void setAuthorizedTypes(String authorizedTypes) {
        this.authorizedTypes = authorizedTypes == null ? null : authorizedTypes.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", authorizedTypes=").append(authorizedTypes);
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
        SysRoleAuthorized other = (SysRoleAuthorized) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()))
            && (this.getAuthorizedTypes() == null ? other.getAuthorizedTypes() == null : this.getAuthorizedTypes().equals(other.getAuthorizedTypes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getMenuCode() == null) ? 0 : getMenuCode().hashCode());
        result = prime * result + ((getAuthorizedTypes() == null) ? 0 : getAuthorizedTypes().hashCode());
        return result;
    }
}