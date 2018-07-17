package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.SysTableAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserCacheResponse;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@ApiOperation(value = "获取菜单")
	@RequestMapping(value = "/menu/authorized", method = RequestMethod.GET)
	public ModelResult<SysRoleMenuResponse> getMenuByUser() {
		SysUserCacheResponse sysUserResponse = Auth.getAuthc(SysUserCacheResponse.class);
		SysRoleMenuResponse menuResponse = null;
		if (sysUserResponse.getAdmin()) {
			menuResponse = sysMenuAuthorizedFeign.getMenuAll().getData();
		} else {
			menuResponse = sysMenuAuthorizedFeign.getMenuByUser(sysUserResponse.getUserId()).getData();
		}
		return Result.build(menuResponse);
	}
	
	@ApiOperation(value = "获取表格")
	@RequestMapping(value = "/menu/authorized/table/{menuId}", method = RequestMethod.GET)
	public ModelResult<SysTableAuthorizedResponse> getActionByMenu(@PathVariable(name = "menuId") Integer menuId) {
		SysUserCacheResponse sysUserResponse = Auth.getAuthc(SysUserCacheResponse.class);
		SysTableAuthorizedResponse authorizedResponse = null;
		if (sysUserResponse.getAdmin()) {
			authorizedResponse = sysMenuAuthorizedFeign.getActionByMenu(menuId).getData();
		} else {
			
		}
		return Result.build(authorizedResponse);
	}
}
