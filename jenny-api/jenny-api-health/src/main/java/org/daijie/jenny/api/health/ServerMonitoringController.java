package org.daijie.jenny.api.health;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.health.request.ServerMonitoringPageRequest;
import org.daijie.jenny.common.feign.health.response.InstanceInfoResponse;
import org.daijie.jenny.common.feign.health.response.ServerMonitoringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.shared.Application;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="服务监控")
@RestController
@RequestMapping("serverMonitoring")
public class ServerMonitoringController {
	
	@Value("${server.admin:server-admin}")
	private final String APPLICATION_URL = "";
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询服务信息")
	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public ModelResult<PageResult<ServerMonitoringResponse>> getApplications(ServerMonitoringPageRequest pageRequest) {
		List<Application> list = restTemplate.getForObject("http://server-admin/applications", List.class);
		long total = list.size();
		List<Application> rows = null;
		if (pageRequest != null && pageRequest.getPageSize() > 0 && pageRequest.getPageNumber() > 0) {
			rows = list.stream()
					.limit(pageRequest.getPageSize() * pageRequest.getPageNumber())
					.skip((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize())
					.collect(Collectors.toList());
		} else {
			rows = list;
		}
		List<ServerMonitoringResponse> serverMonitoringResponses = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String dataString = mapper.writeValueAsString(rows);
			List<Map<String, Object>> datas = mapper.readValue(dataString, List.class);
			datas.forEach(data -> {
				ServerMonitoringResponse serverMonitoringResponse = new ServerMonitoringResponse();
				serverMonitoringResponse.setName((String) data.get("name"));
				serverMonitoringResponse.setStatus((String) data.get("status"));
				serverMonitoringResponse.setBuildVersion((String) data.get("buildVersion"));
				serverMonitoringResponse.setStatusTimestamp((String) data.get("statusTimestamp"));
				if (data.get("instances") instanceof Collection) {
					serverMonitoringResponse.setServerNumber(((Collection<?>) data.get("instances")).size());
					List<Map<String, Object>> instances = (List<Map<String, Object>>) ((Collection<?>) data.get("instances"));
					instances.forEach(instance -> {
						Map<String, String> registration = (Map<String, String>) instance.get("registration");
						serverMonitoringResponse.setInstances((String) instance.get("id"), registration.get("serviceUrl"));
					});
				}
				serverMonitoringResponses.add(serverMonitoringResponse);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageResult<ServerMonitoringResponse> pageResult = new PageResult<ServerMonitoringResponse>(serverMonitoringResponses, total);
		return Result.build(pageResult);
	}

	@ApiOperation(value = "获取所有服务加载菜单")
	@RequestMapping(value = "/menuApplications", method = RequestMethod.GET)
	public ModelResult<List<ServerMonitoringResponse>> getMenuApplications() {
		List<ServerMonitoringResponse> list = getApplications(null).getData().getRows();
		return Result.build(list);
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询单个服务详细信息")
	@RequestMapping(value = "/instance/{id}", method = RequestMethod.GET)
	public ModelResult<InstanceInfoResponse> getInstances(@ApiParam(name="服务编号", allowEmptyValue = false) String id) {
		InstanceInfoResponse InstanceInfoResponse = new InstanceInfoResponse();
		Map<String, Object> cpuCount = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/system.cpu.count", Map.class);
		BigDecimal cpuCountNum = new BigDecimal((double) ((List<Map<String, Object>>) cpuCount.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setCpuCount(cpuCountNum);
		
		Map<String, Object> cpuUsage = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/system.cpu.usage", Map.class);
		BigDecimal cpuUsageNum = new BigDecimal((double) ((List<Map<String, Object>>) cpuUsage.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setCpuUsage(cpuUsageNum.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP));
		
		Map<String, Object> cpuProcessUsage = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/process.cpu.usage", Map.class);
		BigDecimal cpuProcessUsageNum = new BigDecimal((double) ((List<Map<String, Object>>) cpuProcessUsage.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setCpuProcessUsage(cpuProcessUsageNum.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP));
		
		Map<String, Object> maxHeap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.max?tag=area:heap", Map.class);
		BigDecimal maxHeapNum = new BigDecimal((double) ((List<Map<String, Object>>) maxHeap.get("measurements")).get(0).get("value"));
		maxHeapNum = maxHeapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setMaxHeap(maxHeapNum);
		
		Map<String, Object> usedHeap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.used?tag=area:heap", Map.class);
		BigDecimal usedHeapNum = new BigDecimal((double)((List<Map<String, Object>>) usedHeap.get("measurements")).get(0).get("value"));
		usedHeapNum = usedHeapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setUsedHeap(usedHeapNum);
		
		Map<String, Object> committedHeap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.committed?tag=area:heap", Map.class);
		BigDecimal committedHeapNum = new BigDecimal((double) ((List<Map<String, Object>>) committedHeap.get("measurements")).get(0).get("value"));
		committedHeapNum = committedHeapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setCommittedHeap(committedHeapNum);
		
		Map<String, Object> maxNonheap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.max?tag=area:nonheap", Map.class);
		BigDecimal maxNonheapNum = new BigDecimal((double) ((List<Map<String, Object>>) maxNonheap.get("measurements")).get(0).get("value"));
		maxNonheapNum = maxNonheapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setMaxNonheap(maxNonheapNum);
		
		Map<String, Object> usedNonheap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.used?tag=area:nonheap", Map.class);
		BigDecimal usedNonheapNum = new BigDecimal((double)((List<Map<String, Object>>) usedNonheap.get("measurements")).get(0).get("value"));
		usedNonheapNum = usedNonheapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setUsedNonheap(usedNonheapNum);
		
		Map<String, Object> committedNonheap = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.committed?tag=area:nonheap", Map.class);
		BigDecimal committedNonheapNum = new BigDecimal((double) ((List<Map<String, Object>>) committedNonheap.get("measurements")).get(0).get("value"));
		committedNonheapNum = committedNonheapNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setCommittedNonheap(committedNonheapNum);
		
		Map<String, Object> usedMetaspace = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.memory.used?tag=area:nonheap,id:Metaspace", Map.class);
		BigDecimal usedMetaspaceNum = new BigDecimal((double)((List<Map<String, Object>>) usedMetaspace.get("measurements")).get(0).get("value"));
		usedMetaspaceNum = usedMetaspaceNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setUsedMetaspace(usedMetaspaceNum);
		
		Map<String, Object> peakThreads = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.threads.peak", Map.class);
		BigDecimal peakThreadsNum = new BigDecimal((double)((List<Map<String, Object>>) peakThreads.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setPeakThreads(peakThreadsNum);
		
		Map<String, Object> liveThreads = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.threads.live", Map.class);
		BigDecimal liveThreadsNum = new BigDecimal((double)((List<Map<String, Object>>) liveThreads.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setLiveThreads(liveThreadsNum);
		
		Map<String, Object> daemonThreads = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.threads.daemon", Map.class);
		BigDecimal daemonThreadsNum = new BigDecimal((double)((List<Map<String, Object>>) daemonThreads.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setDaemonThreads(daemonThreadsNum);
		
		Map<String, Object> gcMemoryAllocated = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.gc.memory.allocated", Map.class);
		BigDecimal gcMemoryAllocatedNum = new BigDecimal((double)((List<Map<String, Object>>) gcMemoryAllocated.get("measurements")).get(0).get("value"));
		gcMemoryAllocatedNum = gcMemoryAllocatedNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setGcMemoryAllocated(gcMemoryAllocatedNum);
		
		Map<String, Object> gcMemoryPromoted = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.gc.memory.promoted", Map.class);
		BigDecimal gcMemoryPromotedNum = new BigDecimal((double)((List<Map<String, Object>>) gcMemoryPromoted.get("measurements")).get(0).get("value"));
		gcMemoryPromotedNum = gcMemoryPromotedNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setGcMemoryPromoted(gcMemoryPromotedNum);
		
		Map<String, Object> gcMaxDataSize = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.gc.max.data.size", Map.class);
		BigDecimal gcMaxDataSizeNum = new BigDecimal((double)((List<Map<String, Object>>) gcMaxDataSize.get("measurements")).get(0).get("value"));
		gcMaxDataSizeNum = gcMaxDataSizeNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setGcMaxDataSize(gcMaxDataSizeNum);
		
		Map<String, Object> gcLiveDataSize = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.gc.live.data.size", Map.class);
		BigDecimal gcLiveDataSizeNum = new BigDecimal((double)((List<Map<String, Object>>) gcLiveDataSize.get("measurements")).get(0).get("value"));
		gcLiveDataSizeNum = gcLiveDataSizeNum.divide(new BigDecimal(1024)).divide(new BigDecimal(1024));
		InstanceInfoResponse.setGcLiveDataSize(gcLiveDataSizeNum);
		
		Map<String, Object> gcPause = restTemplate.getForObject("http://server-admin/instances/"+id+"/actuator/metrics/jvm.gc.pause", Map.class);
		BigDecimal gcPauseNum = new BigDecimal((double)((List<Map<String, Object>>) gcPause.get("measurements")).get(0).get("value"));
		InstanceInfoResponse.setGcPause(gcPauseNum);
		return Result.build(InstanceInfoResponse);
	}
}
