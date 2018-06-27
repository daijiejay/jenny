package org.daijie.jenny.common.feign.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统用户管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysuser")
public interface SysUserFeign {
	
	@ApiOperation(notes = "获取全部用户", value = "获取全部用户")
	@RequestMapping(value = "/query/all", method = RequestMethod.POST)
	public ModelResult<PageResult<SysUserResponse>> getUserAll(SysUserPageRequest sysUserPageRequest);
	
	@ApiOperation(notes = "根据系统用户ID获取用户信息", value = "根据系统用户ID获取用户信息")
	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
	public ModelResult<SysUserResponse> getUserById(@PathVariable(name = "id") Integer id);
	
	@ApiOperation(notes = "根据系统用户名获取用户", value = "根据系统用户名获取用户")
	@RequestMapping(value = "/query/username/{username}", method = RequestMethod.GET)
	public ModelResult<SysUserResponse> getUserByUsername(@PathVariable(name = "username") String username);

	@ApiOperation(notes = "根据系统用户ID获取用户密码", value = "根据系统用户ID获取用户密码")
	@RequestMapping(value = "/query/password/{id}", method = RequestMethod.GET)
	public ModelResult<SysUserPasswordResponse> getUserPasswordById(@PathVariable(name = "id") Integer id);

	@ApiOperation(notes = "添加用户", value = "添加用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysUserResponse> addUser(SysUserAddRequest sysUserRequest);

	@ApiOperation(notes = "更新用户", value = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updateUser(SysUserUpdateRequest sysUserRequest);

	@ApiOperation(notes = "删除用户", value = "删除用户")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ModelResult<SysUserResponse> deleteUser(@PathVariable(name = "id") Integer id);
}
