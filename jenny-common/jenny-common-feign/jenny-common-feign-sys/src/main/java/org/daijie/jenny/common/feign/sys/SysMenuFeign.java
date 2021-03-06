package org.daijie.jenny.common.feign.sys;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysMenuMoveRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysMenuTreeResponse;
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
	
	@ApiOperation(value = "获取所有菜单")
	@RequestMapping(value = "/query/all", method = RequestMethod.POST)
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest);
	
	@ApiOperation(value = "获取菜单树")
	@RequestMapping(value = "/query/tree", method = RequestMethod.GET)
	public ModelResult<List<SysMenuTreeResponse>> getMenuTree();
	
	@ApiOperation(value = "根据编号菜单信息")
	@RequestMapping(value = "/query/{menuId}", method = RequestMethod.GET)
	public ModelResult<SysMenuResponse> getMenu(@PathVariable(name = "menuId") String menuId);

	@ApiOperation(value = "添加菜单")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysMenuResponse> addMenu(SysMenuAddRequest sysMenuRequest);

	@ApiOperation(value = "更新菜单")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysMenuResponse> updateMenu(SysMenuUpdateRequest sysMenuRequest);

	@ApiOperation(value = "删除菜单")
	@RequestMapping(value = "/delete/menuId/{menuId}", method = RequestMethod.DELETE)
	public ModelResult<SysMenuResponse> deleteMenu(@PathVariable(name = "menuId") Integer menuId);
	
	@ApiOperation(value = "移动菜单")
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public ModelResult<Boolean> moveMenu(SysMenuMoveRequest sysMenuRequest);
}
