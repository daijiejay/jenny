package org.daijie.jenny.common.feign.sys.request;

import io.swagger.annotations.ApiModelProperty;
import org.daijie.swagger.result.Page;

@SuppressWarnings("serial")
public class SysServerPageRequest extends Page {

	/**
     * 服务名
     */
	@ApiModelProperty(value = "服务名")
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
