package org.daijie.jenny.cloud.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.SysMenuAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableColumnResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysTableActionMapper;
import org.daijie.jenny.common.mapper.sys.SysTableColumnMapper;
import org.daijie.jenny.common.mapper.sys.SysTableMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.daijie.jenny.common.model.sys.SysMenuAuthorized;
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;
import org.daijie.jenny.common.model.sys.SysTable;
import org.daijie.jenny.common.model.sys.SysTableAction;
import org.daijie.jenny.common.model.sys.SysTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

@RestController
public class SysMenuAuthorizedService implements SysMenuAuthorizedFeign {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysTableMapper sysTableMapper;
	
	@Autowired
	private SysTableColumnMapper sysTableConlumnMapper;
	
	@Autowired
	private SysTableActionMapper sysTableActionMapper;
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysMenuAuthorizedMapper sysMenuAuthorizedMapper;

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuAll() {
		SysRoleMenuResponse menuResponse = new SysRoleMenuResponse();
		List<SysMenu> list1 = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("level", 1).orderByAsc("parentCode").build());
		List<SysMenuAuthorizedResponse> level1 = new ArrayList<SysMenuAuthorizedResponse>();
		list1.forEach(sysMenu -> {
			SysMenuAuthorizedResponse menuAuthorizedResponse = new SysMenuAuthorizedResponse();
			BeanUtil.copyProperties(sysMenu, menuAuthorizedResponse);
			level1.add(menuAuthorizedResponse);
		});
		menuResponse.setLevel1(level1);
		
		List<SysMenu> list2 = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("level", 2).orderByAsc("parentCode").build());
		List<SysMenuAuthorizedResponse> level2 = new ArrayList<SysMenuAuthorizedResponse>();
		list2.forEach(sysMenu -> {
			SysMenuAuthorizedResponse menuAuthorizedResponse = new SysMenuAuthorizedResponse();
			BeanUtil.copyProperties(sysMenu, menuAuthorizedResponse);
			level2.add(menuAuthorizedResponse);
		});
		menuResponse.setLevel2(level2);
		return Result.build(menuResponse);
	}

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuByRoles(Integer... roleIds) {
		SysRoleMenuResponse menuResponse = getMenuAll().getData();
		List<SysMenuAuthorized> menuAuthorities = sysMenuAuthorizedMapper
				.selectByExample(ExampleBuilder.create(SysMenuAuthorized.class).andIn("roleId", Arrays.asList(roleIds)).build());
		
		menuResponse.setLevel1(menuResponse.getLevel1().stream().filter(menu -> {
			return menuAuthorities.stream().anyMatch(authorized -> menu.getMenuId().equals(authorized.getMenuId()));
		}).collect(Collectors.toList()));
		
		menuResponse.setLevel2(menuResponse.getLevel2().stream().filter(menu -> {
			return menuAuthorities.stream().anyMatch(authorized -> menu.getMenuId().equals(authorized.getMenuId()));
//			return menuAuthorities.stream().anyMatch(authorized -> {
//				boolean match = menu.getMenuId().equals(authorized.getMenuId());
//				if (match) {
//					menu.setSysActions(menu.getSysActions().stream().filter(sysAction -> {
//						return Arrays.asList(authorized.getActionIds().split(",")).stream().anyMatch(actionId -> {
//							return new Integer(Integer.parseInt(actionId)).equals(sysAction.getActionId());
//						});
//					}).collect(Collectors.toList()));
//				}
//				return match;
//			});
		}).collect(Collectors.toList()));
		return Result.build(menuResponse);
	}

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuByUser(Integer userId) {
		List<SysRoleAuthorized> list = sysRoleAuthorizedMapper
				.selectByExample(ExampleBuilder.create(SysRoleAuthorized.class).andEqualTo("userId", userId).build());
		if (list.size() == 0) {
			return Result.build(new SysRoleMenuResponse());
		}
		Integer[] roleIds = new Integer[list.size()];
		list.stream().forEach(sysUserAuthorized -> {
			int i = 0;
			roleIds[i] = sysUserAuthorized.getRoleId();
			i ++;
		});
		SysRoleMenuResponse menuResponse = getMenuByRoles(roleIds).getData();
		menuResponse.setUserId(userId);
		return Result.build(menuResponse);
	}

	@Override
	public ModelResult<SysTableAuthorizedResponse> getActionByMenu(Integer menuId) {
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
		if (sysMenu == null) {
			return Result.build("菜单编号不存在", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysTableAuthorizedResponse sysActionAuthorizedResponse = new SysTableAuthorizedResponse();
		List<SysTable> sysTables = sysTableMapper.selectByExample(
				ExampleBuilder.create(SysTable.class).andEqualTo("menuId", menuId).build());
		
		for (SysTable sysTable : sysTables) {
			SysTableResponse sysTableResponse = new SysTableResponse();
			BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
			sysActionAuthorizedResponse.getTables().add(sysTableResponse);
			
			List<SysTableColumn> sysTableColumns = sysTableConlumnMapper.selectByExample(
					ExampleBuilder.create(SysTableColumn.class).andEqualTo("tableId", sysTable.getTableId()).build());
			List<SysTableColumnResponse> columns = new ArrayList<SysTableColumnResponse>();
			sysTableColumns.forEach(sysTableConlumn -> {
				SysTableColumnResponse sysTableConlumnResponse = new SysTableColumnResponse();
				BeanUtil.copyProperties(sysTableConlumn, sysTableConlumnResponse, CopyOptions.create().setIgnoreError(true));
				columns.add(sysTableConlumnResponse);
			});
			sysTableResponse.setColumns(columns);
			
			List<SysTableAction> sysTableActions = sysTableActionMapper.selectByExample(
					ExampleBuilder.create(SysTableAction.class).andEqualTo("tableId", sysTable.getTableId()).build());
			List<SysTableActionResponse> actions = new ArrayList<SysTableActionResponse>();
			sysTableActions.forEach(action -> {
				SysTableActionResponse actionResponse = new SysTableActionResponse();
				BeanUtil.copyProperties(action, actionResponse, CopyOptions.create().setIgnoreError(true));
				actions.add(actionResponse);
			});
			sysTableResponse.setActions(actions);
		}
		return Result.build(sysActionAuthorizedResponse);
	}

}
