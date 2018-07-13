package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.SysActionFeign;
import org.daijie.jenny.common.feign.sys.request.SysActionAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单功能权限管理")
@RestController
@RequestMapping(value = "sysaction")
public class SysActionController {

	@Autowired
	private SysActionFeign sysActionFeign;

	@ApiOperation(value = "条件查询用户")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysActionResponse>> getActionByPage(SysActionPageRequest sysActionRequest) {
		return sysActionFeign.getActionByPage(sysActionRequest);
	}

	@ApiOperation(value = "添加功能")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysActionResponse> addAction(SysActionAddRequest sysActionRequest) {
		return sysActionFeign.addAction(sysActionRequest);
	}

	@ApiOperation(value = "更新功能")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysActionResponse> updateAction(SysActionUpdateRequest sysActionRequest) {
		return sysActionFeign.updateAction(sysActionRequest);
	}

	@ApiOperation(value = "删除功能")
	@RequestMapping(value = "/delete/{actionId}", method = RequestMethod.DELETE)
	public ModelResult<SysActionResponse> deleteAction(@PathVariable(name = "actionId") Integer actionId) {
		return sysActionFeign.deleteAction(actionId);
	}
}
