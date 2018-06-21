package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuResponse implements Serializable {
	
	/**
     * 菜单代码
     */
	@ApiModelProperty(value = "菜单编号", required = true)
    private String menuCode;

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
     * 父级代码
     */
	@ApiModelProperty(value = "菜单父级编号")
    private String parentCode;

    /**
     * 映射路径
     */
	@ApiModelProperty(value = "映射路径", required = true)
    private String mapping;

    /**
     * 权限类型
     */
	@ApiModelProperty(value = "权限类型")
    private String authorityTypes;

    /**
     * 是否禁用
     */
	@ApiModelProperty(value = "是否禁用", required = true)
    private Boolean enable;

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

	public String getAuthorityTypes() {
		return authorityTypes;
	}

	public void setAuthorityTypes(String authorityTypes) {
		this.authorityTypes = authorityTypes;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
