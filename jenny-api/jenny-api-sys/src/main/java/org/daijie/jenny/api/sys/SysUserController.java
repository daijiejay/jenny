package org.daijie.jenny.api.sys;

import java.util.List;
import java.util.Random;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.controller.exception.ApiException;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.encrypt.PasswordUtil;
import org.daijie.jenny.common.feign.sys.SysRoleAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleSelectedPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserAddInfoRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserSetRolesRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateInfoRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdatePasswordRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateProfileRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleSelectedResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserCacheResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.shiro.authc.Auth;
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
	public ModelResult<SysUserResponse> addUser(@RequestBody SysUserAddInfoRequest sysUserAddInfoRequest) {
		SysUserAddRequest sysUserRequest = new SysUserAddRequest();
		BeanUtil.copyProperties(sysUserAddInfoRequest, sysUserRequest);
		if (!StringUtils.isEmpty(sysUserRequest.getPassword())) {
			try {
				String password = getStringRandom(6);
				
				String salt = PasswordUtil.generateSalt();
				String saltPassword = PasswordUtil.generatePassword(password, salt.getBytes());
				
				sysUserRequest.setPassword(saltPassword);
				sysUserRequest.setSalt(salt);
			} catch (Exception e) {
				throw new ApiException("添加用户失败");
			}
		}
		return sysUserFeign.addUser(sysUserRequest);
	}
	
	@ApiOperation(value = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updateUser(@RequestBody SysUserUpdateInfoRequest sysUserUpdateInfoRequest) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		BeanUtil.copyProperties(sysUserUpdateInfoRequest, sysUserRequest);
		return sysUserFeign.updateUser(sysUserRequest);
	}
	
	@ApiOperation(value = "更新登录用户信息")
	@RequestMapping(value = "/update/profile", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updateProfile(@RequestBody SysUserUpdateProfileRequest sysUserUpdateProfileRequest) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		SysUserCacheResponse sysUserResponse = Auth.getAuthc(SysUserCacheResponse.class);
		BeanUtil.copyProperties(sysUserUpdateProfileRequest, sysUserRequest);
		sysUserRequest.setUserId(sysUserResponse.getUserId());
		ModelResult<SysUserResponse> result = sysUserFeign.updateUser(sysUserRequest);
		BeanUtil.copyProperties(result.getData(), sysUserResponse);
		Auth.refreshAuthc(sysUserResponse);
		return result;
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
	
	@ApiOperation(value = "修改密码")
	@RequestMapping(value = "/update/password", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> updatePassword(@RequestBody SysUserUpdatePasswordRequest sysUserUpdatePasswordRequest) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		SysUserCacheResponse sysUserResponse = Auth.getAuthc(SysUserCacheResponse.class);
		SysUserPasswordResponse passwordResponse = sysUserFeign.getUserPasswordById(sysUserResponse.getUserId()).getData();
		sysUserRequest.setUserId(sysUserResponse.getUserId());
		try {
			String oldPassword = Auth.decryptByPriKey(sysUserUpdatePasswordRequest.getOldPassword());
			oldPassword = PasswordUtil.generatePassword(oldPassword, passwordResponse.getSalt().getBytes());
			if (!oldPassword.equals(passwordResponse.getPassword())) {
				return Result.build("旧密码错误", ApiResult.ERROR, ResultCode.CODE_100);
			}
			String newPassword = Auth.decryptByPriKey(sysUserUpdatePasswordRequest.getNewPassword());
			String salt = PasswordUtil.generateSalt();
			String saltPassword = PasswordUtil.generatePassword(newPassword, salt.getBytes());
			
			sysUserRequest.setPassword(saltPassword);
			sysUserRequest.setSalt(salt);
		} catch (Exception e) {
			throw new ApiException("修改密码失败");
		}
		return sysUserFeign.updateUser(sysUserRequest);
	}
	
	@ApiOperation(value = "重置密码")
	@RequestMapping(value = "/reset/password/{userId}", method = RequestMethod.PUT)
	public ModelResult<SysUserResponse> resetPassword(Integer userId) {
		SysUserUpdateRequest sysUserRequest = new SysUserUpdateRequest();
		sysUserRequest.setUserId(userId);
		try {
			String newPassword = getStringRandom(6);
			String salt = PasswordUtil.generateSalt();
			String saltPassword = PasswordUtil.generatePassword(newPassword, salt.getBytes());
			
			sysUserRequest.setPassword(saltPassword);
			sysUserRequest.setSalt(salt);
		} catch (Exception e) {
			throw new ApiException("修改密码失败");
		}
		return sysUserFeign.updateUser(sysUserRequest);
	}
	
	@ApiOperation(value = "获取登录用户")
	@RequestMapping(value = "/userCache", method = RequestMethod.GET)
	public ModelResult<SysUserCacheResponse> loginUserCache() {
		SysUserCacheResponse authc = Auth.getAuthc(SysUserCacheResponse.class);
		if (authc == null) {
			Result.build(ResultCode.CODE_300.getDescription(), ApiResult.ERROR, ResultCode.CODE_300);
		}
		return Result.build(authc);
	}
	
	/**
	 * 生成随机码
	 * @param length 长度
	 * @return 
	 */
	public String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();        
        for(int i = 0; i < length; i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
}
