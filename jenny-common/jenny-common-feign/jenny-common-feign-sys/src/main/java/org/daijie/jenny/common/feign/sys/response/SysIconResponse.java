package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysIconResponse implements Serializable {

	/**
     * 图标编号
     */
	@ApiModelProperty(value = "图标编号", required = true)
    private Integer iconId;

    /**
     * 图标代码
     */
	@ApiModelProperty(value = "图标代码", required = true)
    private String iconCode;

    /**
     * 图标名称
     */
	@ApiModelProperty(value = "图标名称", required = true)
    private String iconName;

	public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	public String getIconCode() {
		return iconCode;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}
