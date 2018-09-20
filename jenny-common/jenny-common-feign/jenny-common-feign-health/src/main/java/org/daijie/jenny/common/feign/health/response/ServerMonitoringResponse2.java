package org.daijie.jenny.common.feign.health.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings({"serial","unused"})
public class ServerMonitoringResponse2 implements Serializable {

	@ApiModelProperty(value = "服务名称", required = true)
	private String name;

	@ApiModelProperty(value = "被调用服务", required = true)
	private List<AmiCount> amiCounts = new ArrayList<>();
	
	@ApiModelProperty(value = "可用性区域", required = true)
	private List<ZoneCount> zoneCounts = new ArrayList<>();

	@ApiModelProperty(value = "当前服务信息", required = true)
	private List<InstanceInfo> instanceInfos = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<AmiCount> getAmiCounts() {
		return amiCounts;
	}

	public void setAmiCounts(String ami, Integer count) {
		this.amiCounts.add(new AmiCount(ami, count));
	}

	public List<ZoneCount> getZoneCounts() {
		return zoneCounts;
	}

	public void setZoneCounts(String zone, Integer count) {
		this.zoneCounts.add(new ZoneCount(zone, count));
	}

	public List<InstanceInfo> getInstanceInfos() {
		return instanceInfos;
	}

	public int setInstanceInfos(String status, Boolean isNotUp) {
		InstanceInfo instanceInfo = new InstanceInfo(status, isNotUp);
		this.instanceInfos.add(instanceInfo);
		return instanceInfos.size() - 1;
	}
	
	public void setInstances(int index, String id, String url, Boolean isHref) {
		InstanceInfo instanceInfo = this.instanceInfos.get(index);
		instanceInfo.setInstances(id, url, isHref);
	}

	private class AmiCount {
		
		@ApiModelProperty(value = "目标", required = true)
		private String ami;

		@ApiModelProperty(value = "数量", required = true)
		private Integer count;

		private AmiCount(String ami, Integer count) {
			this.ami = ami;
			this.count = count;
		}

		private String getAmi() {
			return ami;
		}

		private void setAmi(String ami) {
			this.ami = ami;
		}

		private Integer getCount() {
			return count;
		}

		private void setCount(Integer count) {
			this.count = count;
		}
	}
	
	private class ZoneCount {

		@ApiModelProperty(value = "区域", required = true)
		private String zone;
		
		@ApiModelProperty(value = "数量", required = true)
		private Integer count;

		private ZoneCount(String zone, Integer count) {
			this.zone = zone;
			this.count = count;
		}

		private String getZone() {
			return zone;
		}

		private Integer getCount() {
			return count;
		}
	}
	
	private class InstanceInfo {

		@ApiModelProperty(value = "服务状态", required = true)
		private String status;

		@ApiModelProperty(value = "是否启动", required = true)
		private Boolean isNotUp;

		@ApiModelProperty(value = "集群服务信息", required = true)
		private List<Instance> instances = new ArrayList<>();

		private InstanceInfo(String status, Boolean isNotUp) {
			this.status = status;
			this.isNotUp = isNotUp;
		}

		private String getStatus() {
			return status;
		}

		private Boolean getIsNotUp() {
			return isNotUp;
		}

		private List<Instance> getInstances() {
			return instances;
		}

		private void setInstances(String id, String url, Boolean isHref) {
			this.instances.add(new Instance(id, url, isHref));
		}
	}
	
	private class Instance {

		@ApiModelProperty(value = "服务地址名称", required = true)
		private String id;

		@ApiModelProperty(value = "服务地址", required = true)
		private String url;

		@ApiModelProperty(value = "是否有服务地址", required = true)
		private Boolean isHref;

		private Instance(String id, String url, Boolean isHref) {
			this.id = id;
			this.url = url;
			this.isHref = isHref;
		}

		private String getId() {
			return id;
		}

		private String getUrl() {
			return url;
		}

		private Boolean getIsHref() {
			return isHref;
		}
	}
}
