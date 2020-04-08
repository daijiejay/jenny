package org.daijie.jenny.api.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.SysServerFeign;
import org.daijie.jenny.common.feign.sys.request.SysServerAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.shiro.session.ShiroRedisSession.Redis;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="系统服务管理")
@RestController
@RequestMapping("sysserver")
public class SysServerController {

	@Autowired
	private SysServerFeign sysServerFeign;

	@ApiOperation(value = "条件查询服务")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysServerResponse>> getServer(SysServerPageRequest sysServerRequest) {
		return sysServerFeign.getServer(sysServerRequest);
	}

	@ApiOperation(value = "条件查询服务")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ModelResult<List<SysServerResponse>> getServerAll() {
		List<SysServerResponse> servers = sysServerFeign.getServerAll().getData();
		return Result.build(servers);
	}

	@ApiOperation(value = "添加服务")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysServerResponse> addServer(@RequestBody SysServerAddRequest sysServerRequest) {
		ModelResult<SysServerResponse> result = sysServerFeign.addServer(sysServerRequest);
		Redis.set("servers", sysServerFeign.getServerAll().getData());
		return result;
	}

	@ApiOperation(value = "更新服务")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysServerResponse> updateServer(@RequestBody SysServerUpdateRequest sysServerRequest) {
		ModelResult<SysServerResponse> result = sysServerFeign.updateServer(sysServerRequest);
		Redis.set("servers", sysServerFeign.getServerAll().getData());
		return result;
	}

	@ApiOperation(value = "删除服务")
	@RequestMapping(value = "/delete/{serverId}", method = RequestMethod.DELETE)
	public ModelResult<SysServerResponse> deleteServer(@PathVariable(name = "serverId") Integer serverId) {
		ModelResult<SysServerResponse> result = sysServerFeign.deleteServer(serverId);
		Redis.set("servers", sysServerFeign.getServerAll().getData());
		return result;
	}
}
