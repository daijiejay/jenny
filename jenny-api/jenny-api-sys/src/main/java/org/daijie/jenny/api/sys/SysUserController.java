package org.daijie.jenny.api.sys;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.PasswordUtil;
import org.daijie.jenny.common.feign.sys.SysRoleAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleSelectedPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserSetRolesRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleSelectedResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统用户管理")
@RestController
@RequestMapping(value = "sysuser")
public class SysUserController {

	@Autowired
	private SysUserFeign sysUserFeign;
	
	@Autowired
	private SysRoleFeign sysRoleFeign;
	
	@Autowired
	private SysRoleAuthorizedFeign sysRoleAuthorizedFeign;
	
	@ApiOperation(value = "获取全部用户")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysUserResponse>> getUserAll(SysUserPageRequest sysUserPageRequest) {
		sysUserPageRequest.setCancel(false);
		return sysUserFeign.getUserAll(sysUserPageRequest);
	}
	
	@ApiOperation(value = "添加用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysUserResponse> addUser(@RequestBody SysUserAddRequest sysUserRequest) {
		if (!StringUtils.isEmpty(sysUserRequest.getPassword())) {
			try {
				String salt = PasswordUtil.generateSalt();
				String saltPassword = PasswordUtil.generatePassword(sysUserRequest.getPassword(), salt.getBytes());
				
				sysUserRequest.setPassword(saltPassword);
				sysUserRequest.setSalt(salt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sysUserFeign.addUser(sysUserRequest);
	}
	
	@ApiOperation(value = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updateUser(@RequestBody SysUserUpdateRequest sysUserRequest) {
		if (!StringUtils.isEmpty(sysUserRequest.getPassword())) {
			try {
				String salt = PasswordUtil.generateSalt();
				String saltPassword = PasswordUtil.generatePassword(sysUserRequest.getPassword(), salt.getBytes());
				
				sysUserRequest.setPassword(saltPassword);
				sysUserRequest.setSalt(salt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sysUserFeign.updateUser(sysUserRequest);
	}

	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	public ModelResult<SysUserResponse> deleteUser(Integer userId) {
		return sysUserFeign.deleteUser(userId);
	}
	
	@ApiOperation(value = "禁用用户")
	@RequestMapping(value = "/enable/{userId}", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> enableUser(Integer userId) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		sysUserRequest.setUserId(userId);
		sysUserRequest.setEnable(true);
		return sysUserFeign.updateUser(sysUserRequest);
	}
	
	@ApiOperation(value = "启用用户")
	@RequestMapping(value = "/notenable/{userId}", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> notenableUser(Integer userId) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		sysUserRequest.setUserId(userId);
		sysUserRequest.setEnable(false);
		return sysUserFeign.updateUser(sysUserRequest);
	}
	
	@ApiOperation(value = "根据系统用户ID获取用户信息")
	@RequestMapping(value = "/query/selectedRoles", method = RequestMethod.GET)
	public ModelResult<PageResult<SysRoleSelectedResponse>> getUserRole(SysRoleSelectedPageRequest sysRoleRequest) {
		PageResult<SysRoleResponse> data = sysRoleFeign.getRoleAll(sysRoleRequest).getData();
		List<String> roleCodes = sysRoleAuthorizedFeign.getRolesByUser(sysRoleRequest.getUserId()).getData();
		PageResult<SysRoleSelectedResponse> roles = new PageResult<SysRoleSelectedResponse>();
		data.getRows().forEach(role -> {
			SysRoleSelectedResponse sysRoleSelectedResponse = new SysRoleSelectedResponse();
			BeanUtil.copyProperties(role, sysRoleSelectedResponse, CopyOptions.create().setIgnoreError(true));
			if (roleCodes.stream().anyMatch(roleCode -> roleCode.equals(role.getRoleCode()))) {
				sysRoleSelectedResponse.setSelected(true);
			}
			roles.getRows().add(sysRoleSelectedResponse);
			roles.setTotal(data.getTotal());
		});
		return Result.build(roles);
	}
	
	@ApiOperation(value = "系统用户设置角色")
	@RequestMapping(value = "/setRoles", method = RequestMethod.PUT) 
	public ModelResult<Boolean> setRoles(SysUserSetRolesRequest sysUserRequest) {
		return sysUserFeign.setRoles(sysUserRequest);
	}
}
