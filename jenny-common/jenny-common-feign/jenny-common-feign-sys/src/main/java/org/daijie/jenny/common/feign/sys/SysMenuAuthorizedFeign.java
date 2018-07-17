package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.jenny.common.feign.sys.response.SysTableAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleMenuResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单权限授权管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/menu/authorized")
public interface SysMenuAuthorizedFeign {
	
	@ApiOperation(notes = "获取所有菜单", value = "获取所有菜单")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelResult<SysRoleMenuResponse> getMenuAll();
	
	@ApiOperation(notes = "根据角色编号获取菜单", value = "根据角色编号获取菜单")
	@RequestMapping(value = "/roleIds", method = RequestMethod.GET)
	public ModelResult<SysRoleMenuResponse> getMenuByRoles(Integer... roleIds);
	
	@ApiOperation(notes = "根据用户编号获取菜单", value = "根据用户编号获取菜单")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public ModelResult<SysRoleMenuResponse> getMenuByUser(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(value = "根据用户编号获取菜单")
	@RequestMapping(value = "/action/{menuId}", method = RequestMethod.GET)
	public ModelResult<SysTableAuthorizedResponse> getActionByMenu(@PathVariable(name = "menuId") Integer menuId);
}
