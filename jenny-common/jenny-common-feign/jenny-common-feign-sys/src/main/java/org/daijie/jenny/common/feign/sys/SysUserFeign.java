package org.daijie.jenny.common.feign.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserSetRolesRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysUserCacheResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(description="系统用户管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysuser")
public interface SysUserFeign {
	
	@ApiOperation(value = "条件查询用户")
	@RequestMapping(value = "/query/all", method = RequestMethod.POST)
	public ModelResult<PageResult<SysUserResponse>> getUserAll(@RequestBody SysUserPageRequest sysUserRequest);
	
	@ApiOperation(value = "根据系统用户ID获取用户信息")
	@RequestMapping(value = "/query/{userId}", method = RequestMethod.GET)
	public ModelResult<SysUserResponse> getUserById(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(value = "根据系统用户名获取用户")
	@RequestMapping(value = "/query/username/{username}", method = RequestMethod.GET)
	public ModelResult<SysUserCacheResponse> getUserByUsername(@PathVariable(name = "username") String username);

	@ApiOperation(value = "根据系统用户ID获取用户密码")
	@RequestMapping(value = "/query/password/{userId}", method = RequestMethod.GET)
	public ModelResult<SysUserPasswordResponse> getUserPasswordById(@PathVariable(name = "userId") Integer userId);

	@ApiOperation(value = "添加用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysUserResponse> addUser(@RequestBody SysUserAddRequest sysUserRequest);

	@ApiOperation(value = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updateUser(@RequestBody SysUserUpdateRequest sysUserRequest);

	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	public ModelResult<SysUserResponse> deleteUser(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(value = "系统用户设置角色")
	@RequestMapping(value = "/setRoles", method = RequestMethod.PUT) 
	public ModelResult<Boolean> setRoles(@RequestBody SysUserSetRolesRequest sysUserRequest);
}
