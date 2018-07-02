package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    /**
     * 菜单编号
     */
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

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
     * 父级菜单编号
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 映射路径
     */
    @Column(name = "mapping")
    private String mapping;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 是否禁用
     */
    @Column(name = "enable")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    /**
     * 获取菜单编号
     *
     * @return menu_id - 菜单编号
     */
    public Integer getMenuId() {
        return menuId;
    }

    public SysMenu withMenuId(Integer menuId) {
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
     * 获取父级菜单编号
     *
     * @return parent_id - 父级菜单编号
     */
    public Integer getParentId() {
        return parentId;
    }

    public SysMenu withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    /**
     * 设置父级菜单编号
     *
     * @param parentId 父级菜单编号
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    public SysMenu withSort(Integer sort) {
        this.setSort(sort);
        return this;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    public SysMenu withIcon(String icon) {
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
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", level=").append(level);
        sb.append(", parentId=").append(parentId);
        sb.append(", sort=").append(sort);
        sb.append(", mapping=").append(mapping);
        sb.append(", icon=").append(icon);
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
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getMapping() == null ? other.getMapping() == null : this.getMapping().equals(other.getMapping()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getMapping() == null) ? 0 : getMapping().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        return result;
    }
}