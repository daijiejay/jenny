package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuUpdateRequest implements Serializable {

	/**
	 * 菜单编号
	 */
	@ApiModelProperty(value = "菜单编号", required = true)
	private Integer menuId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	/**
	 * 菜单等级
	 */
	@ApiModelProperty(value = "菜单等级")
	private Integer level;

	/**
	 * 父级菜单编号
	 */
	@ApiModelProperty(value = "父级菜单编号")
	private Integer parentId;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 映射路径
	 */
	@ApiModelProperty(value = "映射路径")
	private String mapping;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	private String icon;

	/**
     * 是否禁用
     */
	@ApiModelProperty(value = "是否禁用")
	private Boolean enable;


	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
