package org.daijie.jenny.common.feign.health.request;

import org.daijie.core.result.Page;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class ServerMonitoringPageRequest extends Page {

	@ApiModelProperty(value = "服务名称")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
