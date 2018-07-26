package org.daijie.jenny.cloud.sys.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysUserService implements SysUserFeign {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;

	@Override
	public ModelResult<PageResult<SysUserResponse>> getUserAll(SysUserPageRequest sysUserPageRequest) {
		PageHelper.startPage(sysUserPageRequest.getPageNumber(), sysUserPageRequest.getPageSize());
		List<SysUser> users = sysUserMapper.selectByExample(sysUserPageRequest.exampleBuild(SysUser.class));
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
		return Result.build(new PageResult<SysUserResponse>(pageInfo.getList(), pageInfo.getTotal(), SysUserResponse.class));
	}

	@Override
	public ModelResult<SysUserResponse> getUserById(Integer userId) {
		SysUserResponse sysUserResponse = new SysUserResponse();
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build(null);
	}
	
	@Override
	public ModelResult<SysUserCacheResponse> getUserByUsername(String username) {
		SysUserCacheResponse sysUserResponse = new SysUserCacheResponse();
		List<SysUser> sysUsers = sysUserMapper.selectByExample(
				ExampleBuilder.create(SysUser.class).andEqualTo("userName", username)
				.andEqualTo("cancel", false)
				.build());
		if (sysUsers.size() == 1) {
			BeanUtil.copyProperties(sysUsers.get(0), sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build(null);
	}

	@Override
	public ModelResult<SysUserPasswordResponse> getUserPasswordById(Integer userId) {
		SysUserPasswordResponse  sysUserPasswordResponse = new SysUserPasswordResponse();
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserPasswordResponse);
			return Result.build(sysUserPasswordResponse);
		}
		return Result.build(null);
	}

	@Override
	@Transactional
	public ModelResult<SysUserResponse> addUser(SysUserAddRequest sysUserRequest) {
		if (sysUserMapper.selectByExample(ExampleBuilder.create(SysUser.class)
				.andEqualTo("userName", sysUserRequest.getUserName())
				.andEqualTo("cancel", false)
				.build()).size() > 0) {
			return Result.build("用户名已存在！", ApiResult.ERROR, ResultCode.CODE_100);
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
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} else {
			return Result.build("缺少参数userId，更新失败！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysUserResponse sysUserResponse = new SysUserResponse();
		BeanUtil.copyProperties(sysUser, sysUserResponse);
		return Result.build(sysUserResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysUserResponse> deleteUser(Integer userId) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		if (sysUser != null) {
			sysUser.setUserId(userId);
			sysUser.setCancel(true);
			sysUserMapper.updateByPrimaryKey(sysUser);
			SysUserResponse sysUserResponse = new SysUserResponse();
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build("无效的用户编号！", ApiResult.ERROR, ResultCode.CODE_102);
	}

	@Override
	@Transactional
	public ModelResult<Boolean> setRoles(SysUserSetRolesRequest sysUserRequest) {
		sysRoleAuthorizedMapper.deleteByExample(ExampleBuilder.create(SysRoleAuthorized.class)
				.andEqualTo("userId", sysUserRequest.getUserId())
				.andNotIn("roleId", sysUserRequest.getRoleIds()).build());
		for (Integer roleId : sysUserRequest.getRoleIds()) {
			if (sysRoleAuthorizedMapper.selectByExample(ExampleBuilder.create(SysRoleAuthorized.class)
					.andEqualTo("userId", sysUserRequest.getUserId())
					.andEqualTo("roleId", roleId).build()).size() > 0) {
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
