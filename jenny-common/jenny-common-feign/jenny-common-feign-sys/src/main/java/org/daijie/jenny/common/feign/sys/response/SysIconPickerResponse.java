package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysIconPickerResponse implements Serializable {

    /**
     * 图标代码集合
     */
	@ApiModelProperty(value = "图标代码", required = true)
    private List<String> iconCodes = new ArrayList<>();

    /**
     * 图标名称集合
     */
	@ApiModelProperty(value = "图标名称", required = true)
    private List<String> iconNames = new ArrayList<>();

	public List<String> getIconCodes() {
		return iconCodes;
	}

	public void setIconCodes(List<String> iconCodes) {
		this.iconCodes = iconCodes;
	}

	public List<String> getIconNames() {
		return iconNames;
	}

	public void setIconNames(List<String> iconNames) {
		this.iconNames = iconNames;
	}

}
