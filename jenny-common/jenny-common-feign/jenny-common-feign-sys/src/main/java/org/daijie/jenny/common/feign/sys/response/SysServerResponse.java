package org.daijie.jenny.common.feign.sys.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysServerResponse implements Serializable {

	/**
     * 服务名
     */
	@ApiModelProperty(value = "服务名", required = true)
    private String serverId;

    /**
     * 服务地址
     */
	@ApiModelProperty(value = "服务地址", required = true)
    private String serverAddr;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerAddr() {
		return serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

}
