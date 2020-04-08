package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.cloud.sys.mapper.SysRoleMenuAuthorizedMapper;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.*;
import org.daijie.jenny.common.mapper.sys.*;
import org.daijie.jenny.common.model.sys.*;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return Result.build(this.sysRoleMenuAuthorizedMapper.selectRoleMenuAuthorized(userId));
	}

	@Override
	public ModelResult<SysTableAuthorizedResponse> getTableByMenu(Integer menuId) {
		SysMenu sysMenu = this.sysMenuMapper.selectById(menuId);
		if (sysMenu == null) {
			return Result.build("菜单编号不存在", Result.ERROR, ResultCode.CODE_102);
		}
		SysTableAuthorizedResponse sysTableAuthorizedResponse = new SysTableAuthorizedResponse();
		List<SysTable> sysTables = sysTableMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("menuId", menuId));
		
		for (SysTable sysTable : sysTables) {
			SysTableResponse sysTableResponse = new SysTableResponse();
			BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
			sysTableAuthorizedResponse.getTables().add(sysTableResponse);
			
			List<SysTableColumn> sysTableColumns = this.sysTableConlumnMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("tableId", sysTable.getTableId())
						.orderByAsc("showSort"));
			List<SysTableColumnResponse> columns = new ArrayList<SysTableColumnResponse>();
			sysTableColumns.forEach(sysTableConlumn -> {
				SysTableColumnResponse sysTableConlumnResponse = new SysTableColumnResponse();
				BeanUtil.copyProperties(sysTableConlumn, sysTableConlumnResponse, CopyOptions.create().setIgnoreError(true));
				columns.add(sysTableConlumnResponse);
			});
			sysTableResponse.setColumns(columns);
			
			List<SysTableAction> sysTableActions = this.sysTableActionMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("tableId", sysTable.getTableId())
							.orderByAsc("showSort"));
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
		List<SysRoleAuthorized> roles = sysRoleAuthorizedMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("userId", userId));
		List<Integer> roleIds = new ArrayList<>();
		roles.forEach(role -> roleIds.add(role.getRoleId()));
		List<SysOperateAuthorized> sysOperateAuthorizeds = sysOperateAuthorizedMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("menuId", menuId)
				.andIn("roleId", roleIds));
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
