package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.cloud.sys.mapper.SysRoleManagerMapper;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.request.SysMenuAuthorizedAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysOperateAuthorizedAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleAuthorizedSetRequest;
import org.daijie.jenny.common.feign.sys.request.SysRolePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysRoleUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuSelectedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysOperateAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysRoleMapper;
import org.daijie.jenny.common.model.sys.SysMenuAuthorized;
import org.daijie.jenny.common.model.sys.SysOperateAuthorized;
import org.daijie.jenny.common.model.sys.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysRoleService implements SysRoleFeign {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysMenuAuthorizedMapper sysMenuAuthorizedMapper;
	
	@Autowired
	private SysOperateAuthorizedMapper sysOperateAuthorizedMapper;
	
	@Autowired
	private SysRoleManagerMapper sysRoleManagerMapper;

	@Override
	public ModelResult<PageResult<SysRoleResponse>> getRoleAll(SysRolePageRequest sysRolePageRequest) {
		return Result.build(sysRolePageRequest.executePage(sysRoleMapper));
	}

	@Override
	@Transactional
	public ModelResult<SysRoleResponse> addRole(SysRoleAddRequest sysRoleRequest) {
		SysRole role = new SysRole();
		BeanUtil.copyProperties(sysRoleRequest, role);
		sysRoleMapper.insertSelective(role);
		SysRoleResponse sysRoleResponse = new SysRoleResponse();
		BeanUtil.copyProperties(role, sysRoleResponse);
		return Result.build(sysRoleResponse);
	}

	@Override
	@Transactional
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
	@Transactional
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

	@Override
	public ModelResult<List<SysMenuSelectedResponse>> getRoleMenu(Integer roleId) {
		return Result.build(sysRoleManagerMapper.selectSelectedMenuAndOpreate(roleId));
	}

	@Override
	@Transactional
	public ModelResult<Boolean> updateRoleMenu(@RequestBody SysRoleAuthorizedSetRequest sysRoleAuthorizedSetRequest) {
		//更新菜单权限
		if (sysRoleAuthorizedSetRequest.getMenuIds().isEmpty()) {
			sysMenuAuthorizedMapper.deleteByExample(ExampleBuilder.create(SysMenuAuthorized.class)
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()).build());
			
		} else {
			sysMenuAuthorizedMapper.deleteByExample(ExampleBuilder.create(SysMenuAuthorized.class)
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())
					.andNotIn("menuId", sysRoleAuthorizedSetRequest.getMenuIds()).build());
		}
		for (SysMenuAuthorizedAddRequest sysMenuAuthorizedAddRequest : sysRoleAuthorizedSetRequest.getSysMenuAuthorizedRequests()) {
			if (sysMenuAuthorizedMapper.selectByExample(ExampleBuilder.create(SysMenuAuthorized.class)
					.andEqualTo("menuId", sysMenuAuthorizedAddRequest.getMenuId())
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()).build()).size() > 0) {
				continue;		
			}
			SysMenuAuthorized sysMenuAuthorized = new SysMenuAuthorized();
			sysMenuAuthorized.setMenuId(sysMenuAuthorizedAddRequest.getMenuId());
			sysMenuAuthorized.setRoleId(sysRoleAuthorizedSetRequest.getRoleId());
			sysMenuAuthorizedMapper.insertSelective(sysMenuAuthorized);
		}
		
		//更新操作权限
		if (sysRoleAuthorizedSetRequest.getMenuIds().isEmpty()) {
			sysOperateAuthorizedMapper.deleteByExample(ExampleBuilder.create(SysOperateAuthorized.class)
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()).build());
		} else {
			sysOperateAuthorizedMapper.deleteByExample(ExampleBuilder.create(SysOperateAuthorized.class)
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())
					.andNotIn("operateId", sysRoleAuthorizedSetRequest.getOperateIds()).build());
		}
		for (SysOperateAuthorizedAddRequest sysOperateAuthorizedAddRequest : sysRoleAuthorizedSetRequest.getSysOperateAuthorizedRequests()) {
			if (sysOperateAuthorizedMapper.selectByExample(ExampleBuilder.create(SysOperateAuthorized.class)
					.andEqualTo("operateId", sysOperateAuthorizedAddRequest.getOperateId())
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()).build()).size() > 0) {
				continue;		
			}
			SysOperateAuthorized sysOperateAuthorized = new SysOperateAuthorized();
			sysOperateAuthorized.setOperateId(sysOperateAuthorizedAddRequest.getOperateId());
			sysOperateAuthorized.setMenuId(sysOperateAuthorizedAddRequest.getMenuId());
			sysOperateAuthorized.setRoleId(sysRoleAuthorizedSetRequest.getRoleId());
			sysOperateAuthorizedMapper.insertSelective(sysOperateAuthorized);
		}
		return Result.build(true);
	}

}
