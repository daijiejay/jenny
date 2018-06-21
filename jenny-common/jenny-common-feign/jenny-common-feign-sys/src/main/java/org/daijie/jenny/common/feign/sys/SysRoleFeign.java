package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysRolePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统角色管理")
@FeignClient(value="${feign.sys}")
public interface SysRoleFeign {
	
	@ApiOperation(notes = "获取所有角色", value = "获取所有角色")
	@RequestMapping(value = "/sysrole/query/all", method = RequestMethod.GET)
	public ModelResult<PageResult<SysRoleResponse>> getRoleAll(SysRolePageRequest sysRolePageRequest);

	@ApiOperation(notes = "添加角色", value = "添加角色")
	@RequestMapping(value = "/sysrole/add", method = RequestMethod.POST)
	public ModelResult<SysRoleResponse> addRole(SysRoleRequest sysRoleRequest);

	@ApiOperation(notes = "更新角色", value = "更新角色")
	@RequestMapping(value = "/sysrole/update", method = RequestMethod.PUT)
	public ModelResult<SysRoleResponse> updateRole(SysRoleRequest sysRoleRequest);

	@ApiOperation(notes = "删除角色", value = "删除角色")
	@RequestMapping(value = "/sysrole/delete", method = RequestMethod.DELETE)
	public ModelResult<SysRoleResponse> deleteRole(String RoleCode);
}
