package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuAuthorizedResponse implements Serializable {

	@ApiModelProperty(value = "角色编号", required = true)
	private String roleCode;
	
	@ApiModelProperty(value = "菜单编号", required = true)
	private String menuCode;
	
	@ApiModelProperty(value = "菜单名称", required = true)
	private String menuName;
	
	@ApiModelProperty(value = "菜单等级", required = true)
	private Integer level;
	
	@ApiModelProperty(value = "父级菜单名称")
	private String parentCode;
	
	@ApiModelProperty(value = "映射路径")
	private String mapping;
	
	@ApiModelProperty(value = "权限集")
	private String authorizedTypes;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getAuthorizedTypes() {
		return authorizedTypes;
	}

	public void setAuthorizedTypes(String authorizedTypes) {
		this.authorizedTypes = authorizedTypes;
	}
}
