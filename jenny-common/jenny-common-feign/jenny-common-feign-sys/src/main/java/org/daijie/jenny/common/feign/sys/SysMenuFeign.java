package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysmenu")
public interface SysMenuFeign {
	
	@ApiOperation(notes = "获取所有菜单", value = "获取所有菜单")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest);

	@ApiOperation(notes = "添加菜单", value = "添加菜单")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysMenuResponse> addMenu(SysMenuRequest sysMenuRequest);

	@ApiOperation(notes = "更新菜单", value = "更新菜单")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysMenuResponse> updateMenu(SysMenuRequest sysMenuRequest);

	@ApiOperation(notes = "删除菜单", value = "删除菜单")
	@RequestMapping(value = "/delete/menuCode/{menuCode}", method = RequestMethod.DELETE)
	public ModelResult<SysMenuResponse> deleteMenu(@PathVariable(name = "menuCode") String menuCode);
}
