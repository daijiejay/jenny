package org.daijie.jenny.common.feign.sys.request;

import org.daijie.jdbc.mybatis.example.ExampleExecutePage;
import org.daijie.jenny.common.feign.sys.response.SysIconResponse;
import org.daijie.jenny.common.model.sys.SysIcon;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysIconPageRequest extends ExampleExecutePage<SysIcon, SysIconResponse> {

	/**
     * 图标编号
     */
	@ApiModelProperty(value = "图标编号")
    private Integer iconId;

    /**
     * 图标代码
     */
	@ApiModelProperty(value = "图标代码")
    private String iconCode;

    /**
     * 图标名称
     */
	@ApiModelProperty(value = "图标名称")
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
