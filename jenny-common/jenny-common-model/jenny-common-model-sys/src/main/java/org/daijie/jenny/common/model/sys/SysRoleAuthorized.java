package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role_authorized")
public class SysRoleAuthorized implements Serializable {
    /**
     * 用户角色授权编号
     */
    @Id
    @Column(name = "role_authorized_id")
    private Integer roleAuthorizedId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户角色授权编号
     *
     * @return role_authorized_id - 用户角色授权编号
     */
    public Integer getRoleAuthorizedId() {
        return roleAuthorizedId;
    }

    public SysRoleAuthorized withRoleAuthorizedId(Integer roleAuthorizedId) {
        this.setRoleAuthorizedId(roleAuthorizedId);
        return this;
    }

    /**
     * 设置用户角色授权编号
     *
     * @param roleAuthorizedId 用户角色授权编号
     */
    public void setRoleAuthorizedId(Integer roleAuthorizedId) {
        this.roleAuthorizedId = roleAuthorizedId;
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    public SysRoleAuthorized withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色编号
     *
     * @return role_id - 角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    public SysRoleAuthorized withRoleId(Integer roleId) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleAuthorizedId=").append(roleAuthorizedId);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
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
        return (this.getRoleAuthorizedId() == null ? other.getRoleAuthorizedId() == null : this.getRoleAuthorizedId().equals(other.getRoleAuthorizedId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleAuthorizedId() == null) ? 0 : getRoleAuthorizedId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }
}