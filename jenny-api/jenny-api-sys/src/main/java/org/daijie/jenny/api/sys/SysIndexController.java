package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.SysRoleMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统主页面")
@RestController
@RequestMapping("sysindex")
public class SysIndexController {
	
	@Autowired
	private SysMenuAuthorizedFeign sysMenuAuthorizedFeign;
	
	@ApiOperation(notes = "获取菜单", value = "获取菜单")
	@RequestMapping(value = "/menu/authorized", method = RequestMethod.GET)
	public ModelResult<SysRoleMenuResponse> getMenuByUser() {
		@SuppressWarnings("unused")
		int i = 1/0;
		return sysMenuAuthorizedFeign.getMenuAll();
	}
}
