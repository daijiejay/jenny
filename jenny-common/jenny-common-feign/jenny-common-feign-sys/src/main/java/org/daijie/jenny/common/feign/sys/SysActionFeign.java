package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysActionAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysActionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统功能权限管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysaction")
public interface SysActionFeign {

	@ApiOperation(value = "条件查询功能")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysActionResponse>> getActionByPage(SysActionPageRequest sysActionRequest);

	@ApiOperation(value = "添加功能")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysActionResponse> addAction(SysActionAddRequest sysActionRequest);

	@ApiOperation(value = "更新功能")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysActionResponse> updateAction(SysActionUpdateRequest sysActionRequest);

	@ApiOperation(value = "删除功能")
	@RequestMapping(value = "/delete/{actionId}", method = RequestMethod.DELETE)
	public ModelResult<SysActionResponse> deleteAction(@PathVariable(name = "actionId") Integer actionId);
}
