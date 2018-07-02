package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuAddRequest implements Serializable {

    /**
     * 菜单名称
     */
	@ApiModelProperty(value = "菜单名称", required = true)
    private String menuName;

    /**
     * 菜单等级
     */
	@ApiModelProperty(value = "菜单等级", required = true)
    private Integer level;

    /**
     * 父级菜单编号
     */
	@ApiModelProperty(value = "父级菜单编号")
    private Integer parentId;

	/**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
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
}
