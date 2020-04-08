package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserSetRolesRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysUserCacheResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysUserMapper;
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;
import org.daijie.jenny.common.model.sys.SysUser;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
public class SysUserService implements SysUserFeign {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;

	@Override
	public ModelResult<PageResult<SysUserResponse>> getUserAll(SysUserPageRequest sysUserPageRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysUserPageRequest.getUserName()), wrapper -> wrapper.andEqualTo("userName", sysUserPageRequest.getUserName()))
				.and(StringUtils.isNotEmpty(sysUserPageRequest.getMobile()), wrapper -> wrapper.andEqualTo("mobile", sysUserPageRequest.getMobile()))
				.and(sysUserPageRequest.getUserId() != null, wrapper -> wrapper.andEqualTo("userId", sysUserPageRequest.getUserId()))
				.page(sysUserPageRequest.getPageNumber(), sysUserPageRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysUser> page = this.sysUserMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysUserResponse>(page.getRows(), page.getTotal(), SysUserResponse.class));
	}

	@Override
	public ModelResult<SysUserResponse> getUserById(Integer userId) {
		SysUserResponse sysUserResponse = new SysUserResponse();
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build();
	}
	
	@Override
	public ModelResult<SysUserCacheResponse> getUserByUsername(String username) {
		SysUserCacheResponse sysUserResponse = new SysUserCacheResponse();
		List<SysUser> sysUsers = sysUserMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("userName", username)
				.andEqualTo("cancel", false));
		if (sysUsers.size() == 1) {
			BeanUtil.copyProperties(sysUsers.get(0), sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build();
	}

	@Override
	public ModelResult<SysUserPasswordResponse> getUserPasswordById(Integer userId) {
		SysUserPasswordResponse  sysUserPasswordResponse = new SysUserPasswordResponse();
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserPasswordResponse);
			return Result.build(sysUserPasswordResponse);
		}
		return Result.build();
	}

	@Override
	@Transactional
	public ModelResult<SysUserResponse> addUser(SysUserAddRequest sysUserRequest) {
		if (sysUserMapper.selectCountByWrapper(Wrapper.newWrapper()
				.andEqualTo("userName", sysUserRequest.getUserName())
				.andEqualTo("cancel", false)) > 0) {
			return Result.build("用户名已存在！", Result.ERROR, ResultCode.CODE_100);
		}
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserRequest, sysUser);
		sysUser.setCreateTime(new Date());
		sysUserMapper.insertSelective(sysUser);
		SysUserResponse sysUserResponse = new SysUserResponse();
		BeanUtil.copyProperties(sysUser, sysUserResponse);
		return Result.build(sysUserResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysUserResponse> updateUser(SysUserUpdateRequest sysUserRequest) {
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserRequest, sysUser);
		if (sysUser.getUserId() != null) {
			sysUserMapper.updateSelectiveById(sysUser);
		} else {
			return Result.build("缺少参数userId，更新失败！", Result.ERROR, ResultCode.CODE_102);
		}
		SysUserResponse sysUserResponse = new SysUserResponse();
		BeanUtil.copyProperties(sysUser, sysUserResponse);
		return Result.build(sysUserResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysUserResponse> deleteUser(Integer userId) {
		SysUser sysUser = sysUserMapper.selectById(userId);
		if (sysUser != null) {
			sysUser.setUserId(userId);
			sysUser.setCancel(true);
			sysUserMapper.updateById(sysUser);
			SysUserResponse sysUserResponse = new SysUserResponse();
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build("无效的用户编号！", Result.ERROR, ResultCode.CODE_102);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> setRoles(SysUserSetRolesRequest sysUserRequest) {
		sysRoleAuthorizedMapper.deleteByWrapper(Wrapper.newWrapper()
				.andEqualTo("userId", sysUserRequest.getUserId())
				.andNotIn("roleId", sysUserRequest.getRoleIds()));
		for (Integer roleId : sysUserRequest.getRoleIds()) {
			if (sysRoleAuthorizedMapper.selectCountByWrapper(Wrapper.newWrapper()
					.andEqualTo("userId", sysUserRequest.getUserId())
					.andEqualTo("roleId", roleId)) > 0) {
				continue;		
			}
			SysRoleAuthorized sysRoleAuthorized = new SysRoleAuthorized();
			sysRoleAuthorized.setUserId(sysUserRequest.getUserId());
			sysRoleAuthorized.setRoleId(roleId);
			sysRoleAuthorizedMapper.insertSelective(sysRoleAuthorized);
		}
		return Result.build(true, true);
	}

}
