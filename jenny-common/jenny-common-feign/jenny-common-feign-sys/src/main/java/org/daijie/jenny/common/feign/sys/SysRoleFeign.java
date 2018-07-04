package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysRoleAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysRolePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统角色管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysrole")
public interface SysRoleFeign {
	
	@ApiOperation(notes = "获取所有角色", value = "获取所有角色")
	@RequestMapping(value = "/query/all", method = RequestMethod.POST)
	public ModelResult<PageResult<SysRoleResponse>> getRoleAll(SysRolePageRequest sysRolePageRequest);

	@ApiOperation(notes = "添加角色", value = "添加角色")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysRoleResponse> addRole(SysRoleAddRequest sysRoleRequest);

	@ApiOperation(notes = "更新角色", value = "更新角色")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysRoleResponse> updateRole(SysRoleUpdateRequest sysRoleRequest);

	@ApiOperation(notes = "删除角色", value = "删除角色")
	@RequestMapping(value = "/delete/roleId/{roleId}", method = RequestMethod.DELETE)
	public ModelResult<SysRoleResponse> deleteRole(@PathVariable(name = "roleId") Integer roleId);
}
