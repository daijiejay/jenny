package org.daijie.jenny.cloud.sys.service;

import java.util.Date;
import java.util.List;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.core.util.IdWorker;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.request.SysUserAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysUserUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.jenny.common.mapper.sys.SysUserMapper;
import org.daijie.jenny.common.model.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoleilu.hutool.bean.BeanUtil;

@RestController
public class SysUserService implements SysUserFeign {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public ModelResult<PageResult<SysUserResponse>> getUserAll(SysUserPageRequest sysUserPageRequest) {
		PageHelper.startPage(sysUserPageRequest.getPageNumber(), sysUserPageRequest.getPageSize());
		List<SysUser> users = sysUserMapper.selectByExample(sysUserPageRequest.exampleBuild(SysUser.class));
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
		return Result.build(new PageResult<SysUserResponse>(pageInfo.getList(), pageInfo.getTotal(), SysUserResponse.class));
	}

	@Override
	public ModelResult<SysUserResponse> getUserById(Integer id) {
		SysUserResponse sysUserResponse = new SysUserResponse();
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build(null);
	}
	
	@Override
	public ModelResult<SysUserResponse> getUserByUsername(String username) {
		SysUserResponse sysUserResponse = new SysUserResponse();
		List<SysUser> sysUsers = sysUserMapper.selectByExample(ExampleBuilder.create(SysUser.class).andEqualTo("userName", username));
		if (sysUsers.size() == 1) {
			BeanUtil.copyProperties(sysUsers.get(0), sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build(null);
	}

	@Override
	public ModelResult<SysUserPasswordResponse> getUserPasswordById(Integer id) {
		SysUserPasswordResponse  sysUserPasswordResponse = new SysUserPasswordResponse();
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		if (sysUser != null) {
			BeanUtil.copyProperties(sysUser, sysUserPasswordResponse);
			return Result.build(sysUserPasswordResponse);
		}
		return Result.build(null);
	}

	@Override
	public ModelResult<SysUserResponse> addUser(SysUserAddRequest sysUserRequest) {
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserRequest, sysUser);
		sysUser.setUserCode(IdWorker.getId()+"");
		sysUser.setCreateTime(new Date());
		sysUserMapper.insertSelective(sysUser);
		SysUserResponse sysUserResponse = new SysUserResponse();
		BeanUtil.copyProperties(sysUser, sysUserResponse);
		return Result.build(sysUserResponse);
	}

	@Override
	public ModelResult<SysUserResponse> updateUser(SysUserUpdateRequest sysUserRequest) {
		SysUser sysUser = new SysUser();
		BeanUtil.copyProperties(sysUserRequest, sysUser);
		if (sysUser.getId() != null) {
			sysUserMapper.updateByPrimaryKey(sysUser);
		} else if (StringUtil.isNotEmpty(sysUser.getUserCode())) {
			sysUserMapper.updateByExampleSelective(sysUser, ExampleBuilder.create(SysUser.class).andEqualTo("userCode", sysUserRequest.getUserCode()));
		} else {
			return Result.build("缺少id或userCode，更新失败！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysUserResponse sysUserResponse = new SysUserResponse();
		BeanUtil.copyProperties(sysUser, sysUserResponse);
		return Result.build(sysUserResponse);
	}

	@Override
	public ModelResult<SysUserResponse> deleteUser(Integer id) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		if (sysUser != null) {
			sysUser.setId(id);
			sysUser.setCancel(true);
			sysUserMapper.updateByPrimaryKey(sysUser);
			SysUserResponse sysUserResponse = new SysUserResponse();
			BeanUtil.copyProperties(sysUser, sysUserResponse);
			return Result.build(sysUserResponse);
		}
		return Result.build("无效的用户ID！", ApiResult.ERROR, ResultCode.CODE_102);
	}

}
