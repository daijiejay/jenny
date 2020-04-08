package org.daijie.jenny.common.feign.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.request.SysServerAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(description="系统服务管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysserver")
public interface SysServerFeign {
	
	@ApiOperation(value = "条件查询服务")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysServerResponse>> getServer(@RequestBody SysServerPageRequest sysServerRequest);
	
	@ApiOperation(value = "条件查询服务")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ModelResult<List<SysServerResponse>> getServerAll();

	@ApiOperation(value = "添加服务")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysServerResponse> addServer(@RequestBody SysServerAddRequest sysServerRequest);

	@ApiOperation(value = "更新服务")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysServerResponse> updateServer(@RequestBody SysServerUpdateRequest sysServerRequest);

	@ApiOperation(value = "删除服务")
	@RequestMapping(value = "/delete/{serverId}", method = RequestMethod.DELETE)
	public ModelResult<SysServerResponse> deleteServer(@PathVariable(name = "serverId") Integer serverId);
}
