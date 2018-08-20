package org.daijie.jenny.common.feign.sys.request;

import org.daijie.jdbc.mybatis.example.ExampleExecutePage;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.model.sys.SysMenu;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuPageRequest extends ExampleExecutePage<SysMenu, SysMenuResponse> {

	/**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号")
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
