package org.daijie.jenny.api.health;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.health.request.ServerMonitoringPageRequest;
import org.daijie.jenny.common.feign.health.response.ServerMonitoringResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.AmazonInfo;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Pair;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="服务监控")
@RestController
@RequestMapping("serverMonitoring")
public class ServerMonitoringController {

	@ApiOperation(value = "查询服务信息")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<ServerMonitoringResponse>> getServerMonitorings(ServerMonitoringPageRequest pageRequest) {
		List<Application> sortedApplications = getRegistry().getSortedApplications();
		Map<InstanceInfo.InstanceStatus, List<Pair<String, String>>> instancesByStatus = new HashMap<>();
		List<ServerMonitoringResponse> data = new ArrayList<>();
		for (Application app : sortedApplications) {
			ServerMonitoringResponse serverMonitoringResponse = new ServerMonitoringResponse();
			data.add(serverMonitoringResponse);
			serverMonitoringResponse.setName(app.getName());
			Integer amiCount = 0, zoneCount = 0;
			for (InstanceInfo info : app.getInstances()) {
				String id = info.getId();
				String url = info.getStatusPageUrl();
				InstanceInfo.InstanceStatus status = info.getStatus();
				String ami = "n/a";
				String zone = "";
				if (info.getDataCenterInfo().getName() == DataCenterInfo.Name.Amazon) {
					AmazonInfo dcInfo = (AmazonInfo) info.getDataCenterInfo();
					ami = dcInfo.get(AmazonInfo.MetaDataKey.amiId);
					zone = dcInfo.get(AmazonInfo.MetaDataKey.availabilityZone);
				}
				serverMonitoringResponse.setAmiCounts(ami, ++amiCount);
				serverMonitoringResponse.setZoneCounts(zone, ++zoneCount);
				
				List<Pair<String, String>> list = instancesByStatus.get(status);
				if (list == null) {
					list = new ArrayList<>();
					instancesByStatus.put(status, list);
				}
				list.add(new Pair<>(id, url));
			}
			
			for (Iterator<Map.Entry<InstanceInfo.InstanceStatus, List<Pair<String, String>>>> iter = instancesByStatus
					.entrySet().iterator(); iter.hasNext();) {
				Map.Entry<InstanceInfo.InstanceStatus, List<Pair<String, String>>> entry = iter
						.next();
				List<Pair<String, String>> value = entry.getValue();
				InstanceInfo.InstanceStatus status = entry.getKey();
				int index = serverMonitoringResponse.setInstanceInfos(entry.getKey().name(), status != InstanceInfo.InstanceStatus.UP);
				
				for (Pair<String, String> p : value) {
					String url = p.second();
					boolean isHref = url != null && url.startsWith("http");
					serverMonitoringResponse.setInstances(index, p.first(), url, isHref);
				}
			}
		}
		long total = data.size();
		List<ServerMonitoringResponse> rows = data.stream()
				.limit(pageRequest.getPageSize() * pageRequest.getPageNumber())
				.skip((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize())
				.collect(Collectors.toList());
		return Result.build(new PageResult<ServerMonitoringResponse>(rows, total));
	}

	private PeerAwareInstanceRegistry getRegistry() {
		return getServerContext().getRegistry();
	}

	private EurekaServerContext getServerContext() {
		return EurekaServerContextHolder.getInstance().getServerContext();
	}
}
