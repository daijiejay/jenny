package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysRolePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.jenny.common.mapper.sys.SysRoleMapper;
import org.daijie.jenny.common.model.sys.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysRoleService implements SysRoleFeign {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public ModelResult<PageResult<SysRoleResponse>> getRoleAll(SysRolePageRequest sysRolePageRequest) {
		PageHelper.startPage(sysRolePageRequest.getPageNumber(), sysRolePageRequest.getPageSize());
		SysRole role = new SysRole();
		BeanUtil.copyProperties(sysRolePageRequest, role);
		List<SysRole> users = sysRoleMapper.select(role);
        PageInfo<SysRole> pageInfo = new PageInfo<>(users);
		return Result.build(new PageResult<SysRoleResponse>(pageInfo.getList(), pageInfo.getTotal(), SysRoleResponse.class));
	}

	@Override
	public ModelResult<SysRoleResponse> addRole(SysRoleAddRequest sysRoleRequest) {
		SysRole role = new SysRole();
		BeanUtil.copyProperties(sysRoleRequest, role);
		sysRoleMapper.insert(role);
		SysRoleResponse sysRoleResponse = new SysRoleResponse();
		BeanUtil.copyProperties(role, sysRoleResponse);
		return Result.build(sysRoleResponse);
	}

	@Override
	public ModelResult<SysRoleResponse> updateRole(SysRoleUpdateRequest sysRoleRequest) {
		if (sysRoleRequest.getRoleId() == null) {
			return Result.build("缺少参数roleId，更新失败！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysRole role = new SysRole();
		BeanUtil.copyProperties(sysRoleRequest, role);
		sysRoleMapper.updateByExampleSelective(role, ExampleBuilder.create(SysRole.class).andEqualTo("roleId", sysRoleRequest.getRoleId()).build());
		SysRoleResponse sysRoleResponse = new SysRoleResponse();
		BeanUtil.copyProperties(role, sysRoleResponse);
		return Result.build(sysRoleResponse);
	}

	@Override
	public ModelResult<SysRoleResponse> deleteRole(@PathVariable(name = "roleId") Integer roleId) {
		List<SysRole> list = sysRoleMapper.selectByExample(ExampleBuilder.create(SysRole.class).andEqualTo("roleId", roleId).build());
		if (list.size() == 1) {
			sysRoleMapper.deleteByPrimaryKey(list.get(0).getRoleId());
			SysRoleResponse sysRoleResponse = new SysRoleResponse();
			BeanUtil.copyProperties(list.get(0), sysRoleResponse);
			return Result.build(sysRoleResponse);
		}
		return Result.build("无效的角色编号！", ApiResult.ERROR, ResultCode.CODE_102);
	}

}
