package org.daijie.jenny.common.feign.health.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class InstanceResponse implements Serializable {

	@ApiModelProperty(value = "服务编号", required = true)
	private String instanceId;

	@ApiModelProperty(value = "服务地址", required = true)
	private String serverUrl;

	public InstanceResponse(String instanceId, String serverUrl) {
		super();
		this.instanceId = instanceId;
		this.serverUrl = serverUrl;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public String getServerUrl() {
		return serverUrl;
	}
}
