package org.daijie.jenny.common.feign.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.request.SysIconAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysIconResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(description="系统用户管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysicon")
public interface SysIconFeign {
	
	@ApiOperation(value = "条件查询用户")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysIconResponse>> getIcon(@RequestBody SysIconPageRequest sysIconRequest);
	
	@ApiOperation(value = "条件查询用户")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ModelResult<List<SysIconResponse>> getIconAll();

	@ApiOperation(value = "添加用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysIconResponse> addIcon(@RequestBody SysIconAddRequest sysIconRequest);

	@ApiOperation(value = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysIconResponse> updateIcon(@RequestBody SysIconUpdateRequest sysIconRequest);

	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/delete/{iconId}", method = RequestMethod.DELETE)
	public ModelResult<SysIconResponse> deleteIcon(@PathVariable(name = "iconId") Integer iconId);
}
