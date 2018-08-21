package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysServerUpdateRequest implements Serializable {

	/**
     * 服务名
     */
    @Id
	@ApiModelProperty(value = "服务名", required = true)
    private String serverId;

    /**
     * 服务地址
     */
	@ApiModelProperty(value = "服务地址")
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
