package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysIconAddRequest implements Serializable {

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
