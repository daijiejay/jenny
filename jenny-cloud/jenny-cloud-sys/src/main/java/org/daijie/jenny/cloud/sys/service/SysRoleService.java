package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.cloud.sys.mapper.SysRoleManagerMapper;
import org.daijie.jenny.common.feign.sys.SysRoleFeign;
import org.daijie.jenny.common.feign.sys.request.*;
import org.daijie.jenny.common.feign.sys.response.SysMenuSelectedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysOperateAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysRoleMapper;
import org.daijie.jenny.common.model.sys.SysMenuAuthorized;
import org.daijie.jenny.common.model.sys.SysOperateAuthorized;
import org.daijie.jenny.common.model.sys.SysRole;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

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
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysRolePageRequest.getRoleCode()), wrapper -> wrapper.andEqualTo("roleCode", sysRolePageRequest.getRoleCode()))
				.and(StringUtils.isNotEmpty(sysRolePageRequest.getRoleName()), wrapper -> wrapper.andEqualTo("roleName", sysRolePageRequest.getRoleName()))
				.and(sysRolePageRequest.getRoleId() != null, wrapper -> wrapper.andEqualTo("roleId", sysRolePageRequest.getRoleId()))
				.page(sysRolePageRequest.getPageNumber(), sysRolePageRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysRole> page = this.sysRoleMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysRoleResponse>(page.getRows(), page.getTotal(), SysRoleResponse.class));
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
			return Result.build("缺少参数roleId，更新失败！", Result.ERROR, ResultCode.CODE_102);
		}
		SysRole role = new SysRole();
		BeanUtil.copyProperties(sysRoleRequest, role);
		sysRoleMapper.updateSelectiveByWrapper(role, Wrapper.newWrapper().andEqualTo("roleId", sysRoleRequest.getRoleId()));
		SysRoleResponse sysRoleResponse = new SysRoleResponse();
		BeanUtil.copyProperties(role, sysRoleResponse);
		return Result.build(sysRoleResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysRoleResponse> deleteRole(@PathVariable(name = "roleId") Integer roleId) {
		List<SysRole> list = sysRoleMapper.selectByWrapper(Wrapper.newWrapper().andEqualTo("roleId", roleId));
		if (list.size() == 1) {
			sysRoleMapper.deleteById(list.get(0).getRoleId());
			SysRoleResponse sysRoleResponse = new SysRoleResponse();
			BeanUtil.copyProperties(list.get(0), sysRoleResponse);
			return Result.build(sysRoleResponse);
		}
		return Result.build("无效的角色编号！", Result.ERROR, ResultCode.CODE_102);
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
			sysMenuAuthorizedMapper.deleteByWrapper(Wrapper.newWrapper()
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()));
			
		} else {
			sysMenuAuthorizedMapper.deleteByWrapper(Wrapper.newWrapper()
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())
					.andNotIn("menuId", sysRoleAuthorizedSetRequest.getMenuIds()));
		}
		for (SysMenuAuthorizedAddRequest sysMenuAuthorizedAddRequest : sysRoleAuthorizedSetRequest.getSysMenuAuthorizedRequests()) {
			if (sysMenuAuthorizedMapper.selectCountByWrapper(Wrapper.newWrapper()
					.andEqualTo("menuId", sysMenuAuthorizedAddRequest.getMenuId())
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())) > 0) {
				continue;		
			}
			SysMenuAuthorized sysMenuAuthorized = new SysMenuAuthorized();
			sysMenuAuthorized.setMenuId(sysMenuAuthorizedAddRequest.getMenuId());
			sysMenuAuthorized.setRoleId(sysRoleAuthorizedSetRequest.getRoleId());
			sysMenuAuthorizedMapper.insertSelective(sysMenuAuthorized);
		}
		
		//更新操作权限
		if (sysRoleAuthorizedSetRequest.getMenuIds().isEmpty()) {
			sysOperateAuthorizedMapper.deleteByWrapper(Wrapper.newWrapper()
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId()));
		} else {
			sysOperateAuthorizedMapper.deleteByWrapper(Wrapper.newWrapper()
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())
					.andNotIn("operateId", sysRoleAuthorizedSetRequest.getOperateIds()));
		}
		for (SysOperateAuthorizedAddRequest sysOperateAuthorizedAddRequest : sysRoleAuthorizedSetRequest.getSysOperateAuthorizedRequests()) {
			if (sysOperateAuthorizedMapper.selectByWrapper(Wrapper.newWrapper()
					.andEqualTo("operateId", sysOperateAuthorizedAddRequest.getOperateId())
					.andEqualTo("roleId", sysRoleAuthorizedSetRequest.getRoleId())).size() > 0) {
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
