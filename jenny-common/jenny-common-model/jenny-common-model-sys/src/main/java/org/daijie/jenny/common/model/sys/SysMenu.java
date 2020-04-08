package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_menu")
public class SysMenu implements Serializable {

	/**
	 * 菜单编号	
	 */
	@Id
	@Column(name = "menu_id")
	public Integer menuId;

	/**
	 * 父级菜单代码	
	 */
	@Column(name = "parent_code")
	public Integer parentCode;

	/**
	 * 排序	
	 */
	@Column(name = "sort")
	public Integer sort;

	/**
	 * 映射路径	
	 */
	@Column(name = "mapping")
	public String mapping;

	/**
	 * 菜单等级	
	 */
	@Column(name = "level")
	public Integer level;

	/**
	 * 图标	
	 */
	@Column(name = "icon")
	public String icon;

	/**
	 * 是否禁用	
	 */
	@Column(name = "enable")
	public Boolean enable;

	/**
	 * 菜单名称	
	 */
	@Column(name = "menu_name")
	public String menuName;

	/**
	 * 菜单代码	
	 */
	@Column(name = "menu_code")
	public Integer menuCode;

	/**
	 * 父级菜单编号	
	 */
	@Column(name = "parent_id")
	public Integer parentId;

	/**
	 * 设置父级菜单代码	
	 * @param parentCode 父级菜单代码	
	 */
	public void setParentCode(Integer parentCode) {	
		this.parentCode = parentCode;	
	}

	/**
	 * 获取父级菜单代码	
	 * @return parent_code 父级菜单代码	
	 */
	public Integer getParentCode() {	
		return this.parentCode;	
	}

	/**
	 * 设置排序	
	 * @param sort 排序	
	 */
	public void setSort(Integer sort) {	
		this.sort = sort;	
	}

	/**
	 * 获取排序	
	 * @return sort 排序	
	 */
	public Integer getSort() {	
		return this.sort;	
	}

	/**
	 * 设置映射路径	
	 * @param mapping 映射路径	
	 */
	public void setMapping(String mapping) {	
		this.mapping = mapping;	
	}

	/**
	 * 获取映射路径	
	 * @return mapping 映射路径	
	 */
	public String getMapping() {	
		return this.mapping;	
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
	 * 设置菜单等级	
	 * @param level 菜单等级	
	 */
	public void setLevel(Integer level) {	
		this.level = level;	
	}

	/**
	 * 获取菜单等级	
	 * @return level 菜单等级	
	 */
	public Integer getLevel() {	
		return this.level;	
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
	 * 设置是否禁用	
	 * @param enable 是否禁用	
	 */
	public void setEnable(Boolean enable) {	
		this.enable = enable;	
	}

	/**
	 * 获取是否禁用	
	 * @return enable 是否禁用	
	 */
	public Boolean getEnable() {	
		return this.enable;	
	}

	/**
	 * 设置菜单名称	
	 * @param menuName 菜单名称	
	 */
	public void setMenuName(String menuName) {	
		this.menuName = menuName;	
	}

	/**
	 * 获取菜单名称	
	 * @return menu_name 菜单名称	
	 */
	public String getMenuName() {	
		return this.menuName;	
	}

	/**
	 * 设置菜单代码	
	 * @param menuCode 菜单代码	
	 */
	public void setMenuCode(Integer menuCode) {	
		this.menuCode = menuCode;	
	}

	/**
	 * 获取菜单代码	
	 * @return menu_code 菜单代码	
	 */
	public Integer getMenuCode() {	
		return this.menuCode;	
	}

	/**
	 * 设置父级菜单编号	
	 * @param parentId 父级菜单编号	
	 */
	public void setParentId(Integer parentId) {	
		this.parentId = parentId;	
	}

	/**
	 * 获取父级菜单编号	
	 * @return parent_id 父级菜单编号	
	 */
	public Integer getParentId() {	
		return this.parentId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.menuId == null) ? 0 : this.menuId.hashCode());
		result = prime * result + ((this.parentCode == null) ? 0 : this.parentCode.hashCode());
		result = prime * result + ((this.sort == null) ? 0 : this.sort.hashCode());
		result = prime * result + ((this.mapping == null) ? 0 : this.mapping.hashCode());
		result = prime * result + ((this.level == null) ? 0 : this.level.hashCode());
		result = prime * result + ((this.icon == null) ? 0 : this.icon.hashCode());
		result = prime * result + ((this.enable == null) ? 0 : this.enable.hashCode());
		result = prime * result + ((this.menuName == null) ? 0 : this.menuName.hashCode());
		result = prime * result + ((this.menuCode == null) ? 0 : this.menuCode.hashCode());
		result = prime * result + ((this.parentId == null) ? 0 : this.parentId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", menuId=").append(this.menuId);
		sb.append(", parentCode=").append(this.parentCode);
		sb.append(", sort=").append(this.sort);
		sb.append(", mapping=").append(this.mapping);
		sb.append(", level=").append(this.level);
		sb.append(", icon=").append(this.icon);
		sb.append(", enable=").append(this.enable);
		sb.append(", menuName=").append(this.menuName);
		sb.append(", menuCode=").append(this.menuCode);
		sb.append(", parentId=").append(this.parentId);
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
			&& (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
			&& (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
			&& (this.getMapping() == null ? other.getMapping() == null : this.getMapping().equals(other.getMapping()))
			&& (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
			&& (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
			&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
			&& (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
			&& (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()))
			&& (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
	}

}