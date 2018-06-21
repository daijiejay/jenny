package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 菜单代码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单等级
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 父级代码
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 映射路径
     */
    @Column(name = "mapping")
    private String mapping;

    /**
     * 权限类型
     */
    @Column(name = "authority_types")
    private String authorityTypes;

    /**
     * 是否禁用
     */
    @Column(name = "enable")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public SysMenu withId(Integer id) {
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
     * 获取菜单代码
     *
     * @return menu_code - 菜单代码
     */
    public String getMenuCode() {
        return menuCode;
    }

    public SysMenu withMenuCode(String menuCode) {
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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    public SysMenu withMenuName(String menuName) {
        this.setMenuName(menuName);
        return this;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单等级
     *
     * @return level - 菜单等级
     */
    public Integer getLevel() {
        return level;
    }

    public SysMenu withLevel(Integer level) {
        this.setLevel(level);
        return this;
    }

    /**
     * 设置菜单等级
     *
     * @param level 菜单等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取父级代码
     *
     * @return parent_code - 父级代码
     */
    public String getParentCode() {
        return parentCode;
    }

    public SysMenu withParentCode(String parentCode) {
        this.setParentCode(parentCode);
        return this;
    }

    /**
     * 设置父级代码
     *
     * @param parentCode 父级代码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 获取映射路径
     *
     * @return mapping - 映射路径
     */
    public String getMapping() {
        return mapping;
    }

    public SysMenu withMapping(String mapping) {
        this.setMapping(mapping);
        return this;
    }

    /**
     * 设置映射路径
     *
     * @param mapping 映射路径
     */
    public void setMapping(String mapping) {
        this.mapping = mapping == null ? null : mapping.trim();
    }

    /**
     * 获取权限类型
     *
     * @return authority_types - 权限类型
     */
    public String getAuthorityTypes() {
        return authorityTypes;
    }

    public SysMenu withAuthorityTypes(String authorityTypes) {
        this.setAuthorityTypes(authorityTypes);
        return this;
    }

    /**
     * 设置权限类型
     *
     * @param authorityTypes 权限类型
     */
    public void setAuthorityTypes(String authorityTypes) {
        this.authorityTypes = authorityTypes == null ? null : authorityTypes.trim();
    }

    /**
     * 获取是否禁用
     *
     * @return enable - 是否禁用
     */
    public Boolean getEnable() {
        return enable;
    }

    public SysMenu withEnable(Boolean enable) {
        this.setEnable(enable);
        return this;
    }

    /**
     * 设置是否禁用
     *
     * @param enable 是否禁用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuName=").append(menuName);
        sb.append(", level=").append(level);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", mapping=").append(mapping);
        sb.append(", authorityTypes=").append(authorityTypes);
        sb.append(", enable=").append(enable);
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
        SysMenu other = (SysMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
            && (this.getMapping() == null ? other.getMapping() == null : this.getMapping().equals(other.getMapping()))
            && (this.getAuthorityTypes() == null ? other.getAuthorityTypes() == null : this.getAuthorityTypes().equals(other.getAuthorityTypes()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenuCode() == null) ? 0 : getMenuCode().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getParentCode() == null) ? 0 : getParentCode().hashCode());
        result = prime * result + ((getMapping() == null) ? 0 : getMapping().hashCode());
        result = prime * result + ((getAuthorityTypes() == null) ? 0 : getAuthorityTypes().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        return result;
    }
}