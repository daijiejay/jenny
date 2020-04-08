package org.daijie.jenny.api.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleAuthorizedSetRequest;
import org.daijie.jenny.common.feign.sys.request.SysRolePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuSelectedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="系统角色管理")
@RestController
@RequestMapping(value = "sysrole")
public class SysRoleController {
	
	@Autowired
	private SysRoleFeign sysRoleFeign;
	
	@ApiOperation(value = "获取全部角色")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysRoleResponse>> getRoleAll(SysRolePageRequest sysRoleRequest) {
		return sysRoleFeign.getRoleAll(sysRoleRequest);
	}
	
	@ApiOperation(value = "添加角色")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysRoleResponse> addRole(@RequestBody SysRoleAddRequest sysRoleRequest) {
		return sysRoleFeign.addRole(sysRoleRequest);
	}
	
	@ApiOperation(value = "更新角色")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysRoleResponse> updateRole(@RequestBody SysRoleUpdateRequest sysRoleRequest) {
		return sysRoleFeign.updateRole(sysRoleRequest);
	}

	@ApiOperation(value = "删除角色")
	@RequestMapping(value = "/delete/{roleId}", method = RequestMethod.DELETE)
	public ModelResult<SysRoleResponse> deleteRole(@PathVariable("roleId") Integer roleId) {
		return sysRoleFeign.deleteRole(roleId);
	}
	
	@ApiOperation(value = "禁用角色")
	@RequestMapping(value = "/enable/{roleId}", method = RequestMethod.PUT)
	public ModelResult<SysRoleResponse> enableRole(@PathVariable("roleId") Integer roleId) {
		SysRoleUpdateRequest sysRoleRequest = new SysRoleUpdateRequest();
		sysRoleRequest.setRoleId(roleId);
		sysRoleRequest.setEnable(true);
		return sysRoleFeign.updateRole(sysRoleRequest);
	}
	
	@ApiOperation(value = "启用角色")
	@RequestMapping(value = "/notenable/{roleId}", method = RequestMethod.PUT)
	public ModelResult<SysRoleResponse> notenableRole(@PathVariable("roleId") Integer roleId) {
		SysRoleUpdateRequest sysRoleRequest = new SysRoleUpdateRequest();
		sysRoleRequest.setRoleId(roleId);
		sysRoleRequest.setEnable(false);
		return sysRoleFeign.updateRole(sysRoleRequest);
	}
	
	@ApiOperation(value = "根据系统用户ID获取用户信息")
	@RequestMapping(value = "/query/selectedMenus/{roleId}", method = RequestMethod.GET)
	public ModelResult<List<SysMenuSelectedResponse>> getRoleMenu(@PathVariable("roleId") Integer roleId) {
		return sysRoleFeign.getRoleMenu(roleId);
	}
	
	@ApiOperation(value = "角色设置菜单操作权限")
	@RequestMapping(value = "/update/selectedMenus", method = RequestMethod.PUT)
	public ModelResult<Boolean> updateRoleMenu(@RequestBody SysRoleAuthorizedSetRequest sysRoleAuthorizedSetRequest) {
		return sysRoleFeign.updateRoleMenu(sysRoleAuthorizedSetRequest);
	}
}
