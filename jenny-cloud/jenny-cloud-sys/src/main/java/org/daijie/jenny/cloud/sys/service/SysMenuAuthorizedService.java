package org.daijie.jenny.cloud.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.cloud.sys.mapper.SysRoleMenuAuthorizedMapper;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableColumnResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.mapper.sys.SysOperateAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysTableActionMapper;
import org.daijie.jenny.common.mapper.sys.SysTableColumnMapper;
import org.daijie.jenny.common.mapper.sys.SysTableMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.daijie.jenny.common.model.sys.SysOperateAuthorized;
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
	private SysRoleMenuAuthorizedMapper sysRoleMenuAuthorizedMapper;
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysOperateAuthorizedMapper sysOperateAuthorizedMapper;

	@Override
	public ModelResult<List<SysMenuResponse>> getMenuAuthrozied(Integer userId) {
		return Result.build(sysRoleMenuAuthorizedMapper.selectRoleMenuAuthorized(userId));
	}

	@Override
	public ModelResult<SysTableAuthorizedResponse> getTableByMenu(Integer menuId) {
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
		if (sysMenu == null) {
			return Result.build("菜单编号不存在", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysTableAuthorizedResponse sysTableAuthorizedResponse = new SysTableAuthorizedResponse();
		List<SysTable> sysTables = sysTableMapper.selectByExample(
				ExampleBuilder.create(SysTable.class).andEqualTo("menuId", menuId).build());
		
		for (SysTable sysTable : sysTables) {
			SysTableResponse sysTableResponse = new SysTableResponse();
			BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
			sysTableAuthorizedResponse.getTables().add(sysTableResponse);
			
			List<SysTableColumn> sysTableColumns = sysTableConlumnMapper.selectByExample(
					ExampleBuilder.create(SysTableColumn.class).andEqualTo("tableId", sysTable.getTableId())
					.orderByAsc("showSort").build());
			List<SysTableColumnResponse> columns = new ArrayList<SysTableColumnResponse>();
			sysTableColumns.forEach(sysTableConlumn -> {
				SysTableColumnResponse sysTableConlumnResponse = new SysTableColumnResponse();
				BeanUtil.copyProperties(sysTableConlumn, sysTableConlumnResponse, CopyOptions.create().setIgnoreError(true));
				columns.add(sysTableConlumnResponse);
			});
			sysTableResponse.setColumns(columns);
			
			List<SysTableAction> sysTableActions = sysTableActionMapper.selectByExample(
					ExampleBuilder.create(SysTableAction.class).andEqualTo("tableId", sysTable.getTableId())
					.orderByAsc("showSort").build());
			List<SysTableActionResponse> actions = new ArrayList<SysTableActionResponse>();
			sysTableActions.forEach(action -> {
				SysTableActionResponse actionResponse = new SysTableActionResponse();
				BeanUtil.copyProperties(action, actionResponse, CopyOptions.create().setIgnoreError(true));
				actions.add(actionResponse);
			});
			sysTableResponse.setActions(actions);
		}
		return Result.build(sysTableAuthorizedResponse);
	}

	@Override
	public ModelResult<SysTableAuthorizedResponse> getTableAuthrozied(Integer menuId, Integer userId) {
		SysTableAuthorizedResponse sysTableAuthorizedResponse = getTableByMenu(menuId).getData();
		List<SysRoleAuthorized> roles = sysRoleAuthorizedMapper.selectByExample(
				ExampleBuilder.create(SysRoleAuthorized.class).andEqualTo("userId", userId).build());
		List<Integer> roleIds = new ArrayList<>();
		roles.forEach(role -> roleIds.add(role.getRoleId()));
		List<SysOperateAuthorized> sysOperateAuthorizeds = sysOperateAuthorizedMapper.selectByExample(
				ExampleBuilder.create(SysOperateAuthorized.class)
				.andEqualTo("menuId", menuId)
				.andIn("roleId", roleIds)
				.build());
		sysTableAuthorizedResponse.getTables().forEach(table -> {
			if (sysOperateAuthorizeds.isEmpty()) {
				table.getActions().clear();
			} else {
				table.setActions(table.getActions().stream().filter(action -> {
					return sysOperateAuthorizeds.stream().anyMatch(sysOperateAuthorized -> action.getActionId().equals(sysOperateAuthorized));
				}).collect(Collectors.toList()));
			}
		});
		return Result.build(sysTableAuthorizedResponse);
	}

}
