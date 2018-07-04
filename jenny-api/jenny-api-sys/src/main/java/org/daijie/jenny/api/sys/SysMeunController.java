package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.SysMenuFeign;
import org.daijie.jenny.common.feign.sys.request.SysMenuAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单管理")
@RestController
@RequestMapping(value = "sysmeun")
public class SysMeunController {
	
	@Autowired
	private SysMenuFeign sysMenuFeign;
	
	@ApiOperation(notes = "获取全部菜单", value = "获取全部菜单")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuRequest) {
		return sysMenuFeign.getMenuAll(sysMenuRequest);
	}
	
	@ApiOperation(notes = "添加菜单", value = "添加菜单")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysMenuResponse> addMenu(SysMenuAddRequest sysMenuRequest) {
		return sysMenuFeign.addMenu(sysMenuRequest);
	}
	
	@ApiOperation(notes = "更新菜单", value = "更新菜单")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysMenuResponse> updateMenu(SysMenuUpdateRequest sysMenuRequest) {
		return sysMenuFeign.updateMenu(sysMenuRequest);
	}

	@ApiOperation(notes = "删除菜单", value = "删除菜单")
	@RequestMapping(value = "/delete/{menuId}", method = RequestMethod.DELETE)
	public ModelResult<SysMenuResponse> deleteMenu(Integer menuId) {
		return sysMenuFeign.deleteMenu(menuId);
	}
	
	@ApiOperation(notes = "禁用菜单", value = "禁用菜单")
	@RequestMapping(value = "/enable/{menuId}", method = RequestMethod.PUT)
	public ModelResult<SysMenuResponse> enableMenu(Integer menuId) {
		SysMenuUpdateRequest sysMenuRequest = new SysMenuUpdateRequest();
		sysMenuRequest.setMenuId(menuId);
		sysMenuRequest.setEnable(true);
		return sysMenuFeign.updateMenu(sysMenuRequest);
	}
	
	@ApiOperation(notes = "启用菜单", value = "启用菜单")
	@RequestMapping(value = "/notenable/{menuId}", method = RequestMethod.PUT)
	public ModelResult<SysMenuResponse> notenableMenu(Integer menuId) {
		SysMenuUpdateRequest sysMenuRequest = new SysMenuUpdateRequest();
		sysMenuRequest.setMenuId(menuId);
		sysMenuRequest.setEnable(false);
		return sysMenuFeign.updateMenu(sysMenuRequest);
	}
}
