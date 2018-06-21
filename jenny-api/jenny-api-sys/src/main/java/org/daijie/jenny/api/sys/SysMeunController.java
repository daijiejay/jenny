package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.SysMenuFeign;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单管理")
@RestController
@RequestMapping("sysmeun")
public class SysMeunController {
	
	@Autowired
	private SysMenuFeign sysMenuFeign;
	
	@ApiOperation(notes = "获取全部菜单", value = "获取全部菜单")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest) {
		return sysMenuFeign.getMenuAll(sysMenuPageRequest);
	}
}
