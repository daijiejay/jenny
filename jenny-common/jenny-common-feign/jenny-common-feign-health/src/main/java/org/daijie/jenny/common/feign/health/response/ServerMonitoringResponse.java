package org.daijie.jenny.common.feign.health.response;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings({"serial"})
public class ServerMonitoringResponse implements Serializable {

	@ApiModelProperty(value = "服务名称", required = true)
	private String name;

	@ApiModelProperty(value = "版本", required = true)
	private String buildVersion;
	
	@ApiModelProperty(value = "状态", required = true)
    private String status;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "构建时间", required = true)
    private Date statusTimestamp;

	@ApiModelProperty(value = "服务数量", required = true)
	private Integer serverNumber;

	@ApiModelProperty(value = "服务数组", required = true)
	private List<InstanceResponse> instances = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusTimestamp() {
		return statusTimestamp;
	}

	public void setStatusTimestamp(String statusTimestamp) {
		statusTimestamp = statusTimestamp.replace("Z", " UTC");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		try {
			this.statusTimestamp = sdf.parse(statusTimestamp);
		} catch (ParseException e) {
		}
	}

	public Integer getServerNumber() {
		return serverNumber;
	}

	public void setServerNumber(Integer serverNumber) {
		this.serverNumber = serverNumber;
	}
	
	public List<InstanceResponse> getInstances() {
		return instances;
	}

	public void setInstances(String instanceId, String serverUrl) {
		InstanceResponse instance = new InstanceResponse(instanceId, serverUrl);
		this.instances.add(instance);
	}
}
