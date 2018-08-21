package org.daijie.jenny.common.feign.sys.request;

import javax.persistence.Id;

import org.daijie.jdbc.mybatis.example.ExampleExecutePage;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.jenny.common.model.sys.SysServer;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysServerPageRequest extends ExampleExecutePage<SysServer, SysServerResponse> {

	/**
     * 服务名
     */
    @Id
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
