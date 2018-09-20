package org.daijie.jenny.common.feign.health.response;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(description = "服务状态详细信息")
public class InstanceInfoResponse implements Serializable {

	@ApiModelProperty(value = "服务编号", required = true)
	private String instanceId;

	@ApiModelProperty(value = "CPU数量", required = true)
	private BigDecimal cpuCount;
	
	@ApiModelProperty(value = "系统CPU使用率", required = true)
	private BigDecimal cpuUsage;
	
	@ApiModelProperty(value = "当前进程CPU使用率", required = true)
	private BigDecimal cpuProcessUsage;
	
	@ApiModelProperty(value = "JVM最大堆内存", required = true)
	private BigDecimal maxHeap;
	
	@ApiModelProperty(value = "JVM已用堆内存", required = true)
	private BigDecimal usedHeap;
	
	@ApiModelProperty(value = "JVM可用堆内存", required = true)
	private BigDecimal committedHeap;
	
	@ApiModelProperty(value = "JVM最大非堆内存", required = true)
	private BigDecimal maxNonheap;
	
	@ApiModelProperty(value = "JVM已用非堆内存", required = true)
	private BigDecimal usedNonheap;
	
	@ApiModelProperty(value = "JVM可用非堆内存", required = true)
	private BigDecimal committedNonheap;
	
	@ApiModelProperty(value = "JVM已用元空间", required = true)
	private BigDecimal usedMetaspace;
	
	@ApiModelProperty(value = "JVM缓冲区已用内存", required = true)
	private BigDecimal usedBufferMemory;
	
	@ApiModelProperty(value = "JVM缓冲区数量", required = true)
	private BigDecimal bufferCount;
	
	@ApiModelProperty(value = "JVM守护线程数", required = true)
	private BigDecimal daemonThreads;
	
	@ApiModelProperty(value = "JVM当前活跃线程数", required = true)
	private BigDecimal liveThreads;
	
	@ApiModelProperty(value = "JVM峰值线程数", required = true)
	private BigDecimal peakThreads;
	
	@ApiModelProperty(value = "加载classes数", required = true)
	private BigDecimal loadedClasses;
	
	@ApiModelProperty(value = "未加载classes数", required = true)
	private BigDecimal unloadedClasses;
	
	@ApiModelProperty(value = "GC时，年轻代分配的内存空间", required = true)
	private BigDecimal gcMemoryAllocated;
	
	@ApiModelProperty(value = "GC时，老年代分配的内存空间", required = true)
	private BigDecimal gcMemoryPromoted;
	
	@ApiModelProperty(value = "GC时，老年代的最大内存空间", required = true)
	private BigDecimal gcMaxDataSize;
	
	@ApiModelProperty(value = "FullGC时，老年代的内存空间", required = true)
	private BigDecimal gcLiveDataSize;
	
	@ApiModelProperty(value = "GC耗时", required = true)
	private BigDecimal gcPause;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public BigDecimal getMaxHeap() {
		return maxHeap;
	}

	public void setMaxHeap(BigDecimal maxHeap) {
		this.maxHeap = maxHeap;
	}

	public BigDecimal getUsedHeap() {
		return usedHeap;
	}

	public void setUsedHeap(BigDecimal usedHeap) {
		this.usedHeap = usedHeap;
	}

	public BigDecimal getCommittedHeap() {
		return committedHeap;
	}

	public void setCommittedHeap(BigDecimal committedHeap) {
		this.committedHeap = committedHeap;
	}

	public BigDecimal getMaxNonheap() {
		return maxNonheap;
	}

	public void setMaxNonheap(BigDecimal maxNonheap) {
		this.maxNonheap = maxNonheap;
	}

	public BigDecimal getUsedNonheap() {
		return usedNonheap;
	}

	public void setUsedNonheap(BigDecimal usedNonheap) {
		this.usedNonheap = usedNonheap;
	}

	public BigDecimal getCommittedNonheap() {
		return committedNonheap;
	}

	public void setCommittedNonheap(BigDecimal committedNonheap) {
		this.committedNonheap = committedNonheap;
	}

	public BigDecimal getUsedMetaspace() {
		return usedMetaspace;
	}

	public void setUsedMetaspace(BigDecimal usedMetaspace) {
		this.usedMetaspace = usedMetaspace;
	}

	public BigDecimal getUsedBufferMemory() {
		return usedBufferMemory;
	}

	public void setUsedBufferMemory(BigDecimal usedBufferMemory) {
		this.usedBufferMemory = usedBufferMemory;
	}

	public BigDecimal getBufferCount() {
		return bufferCount;
	}

	public void setBufferCount(BigDecimal bufferCount) {
		this.bufferCount = bufferCount;
	}

	public BigDecimal getDaemonThreads() {
		return daemonThreads;
	}

	public void setDaemonThreads(BigDecimal daemonThreads) {
		this.daemonThreads = daemonThreads;
	}

	public BigDecimal getLiveThreads() {
		return liveThreads;
	}

	public void setLiveThreads(BigDecimal liveThreads) {
		this.liveThreads = liveThreads;
	}

	public BigDecimal getPeakThreads() {
		return peakThreads;
	}

	public void setPeakThreads(BigDecimal peakThreads) {
		this.peakThreads = peakThreads;
	}

	public BigDecimal getLoadedClasses() {
		return loadedClasses;
	}

	public void setLoadedClasses(BigDecimal loadedClasses) {
		this.loadedClasses = loadedClasses;
	}

	public BigDecimal getUnloadedClasses() {
		return unloadedClasses;
	}

	public void setUnloadedClasses(BigDecimal unloadedClasses) {
		this.unloadedClasses = unloadedClasses;
	}

	public BigDecimal getGcMemoryAllocated() {
		return gcMemoryAllocated;
	}

	public void setGcMemoryAllocated(BigDecimal gcMemoryAllocated) {
		this.gcMemoryAllocated = gcMemoryAllocated;
	}

	public BigDecimal getGcMemoryPromoted() {
		return gcMemoryPromoted;
	}

	public void setGcMemoryPromoted(BigDecimal gcMemoryPromoted) {
		this.gcMemoryPromoted = gcMemoryPromoted;
	}

	public BigDecimal getGcMaxDataSize() {
		return gcMaxDataSize;
	}

	public void setGcMaxDataSize(BigDecimal gcMaxDataSize) {
		this.gcMaxDataSize = gcMaxDataSize;
	}

	public BigDecimal getGcLiveDataSize() {
		return gcLiveDataSize;
	}

	public void setGcLiveDataSize(BigDecimal gcLiveDataSize) {
		this.gcLiveDataSize = gcLiveDataSize;
	}

	public BigDecimal getGcPause() {
		return gcPause;
	}

	public void setGcPause(BigDecimal gcPause) {
		this.gcPause = gcPause;
	}

	public BigDecimal getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(BigDecimal cpuCount) {
		this.cpuCount = cpuCount;
	}

	public BigDecimal getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(BigDecimal cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public BigDecimal getCpuProcessUsage() {
		return cpuProcessUsage;
	}

	public void setCpuProcessUsage(BigDecimal cpuProcessUsage) {
		this.cpuProcessUsage = cpuProcessUsage;
	}
}
