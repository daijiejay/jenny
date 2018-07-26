package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuAuthorizedResponse implements Serializable {

	@ApiModelProperty(value = "角色编号", required = true)
	private Integer roleId;
	
	@ApiModelProperty(value = "菜单编号", required = true)
	private Integer menuId;
	
	@ApiModelProperty(value = "菜单名称", required = true)
	private String menuName;
	
	@ApiModelProperty(value = "菜单等级", required = true)
	private Integer level;
	
	@ApiModelProperty(value = "父级菜单名称")
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

	@ApiModelProperty(value = "功能编号集合")
	private String actionIds;

	@ApiModelProperty(value = "功能集合", required = true)
	private List<SysTableActionResponse> actions = new ArrayList<SysTableActionResponse>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

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

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getActionIds() {
		return actionIds;
	}

	public void setActionIds(String actionIds) {
		this.actionIds = actionIds;
	}

	public List<SysTableActionResponse> getActions() {
		return actions;
	}

	public void setActions(List<SysTableActionResponse> actions) {
		this.actions = actions;
	}
}
