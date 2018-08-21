package org.daijie.jenny.cloud.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysTableFeign;
import org.daijie.jenny.common.feign.sys.request.SysTableActionAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableActionPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableActionUpdateRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableColumnAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableColumnPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableColumnUpdateRequest;
import org.daijie.jenny.common.feign.sys.request.SysTablePageRequest;
import org.daijie.jenny.common.feign.sys.request.SysTableUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuTreeResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableColumnResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.jenny.common.mapper.sys.SysTableActionMapper;
import org.daijie.jenny.common.mapper.sys.SysTableColumnMapper;
import org.daijie.jenny.common.mapper.sys.SysTableMapper;
import org.daijie.jenny.common.model.sys.SysTable;
import org.daijie.jenny.common.model.sys.SysTableAction;
import org.daijie.jenny.common.model.sys.SysTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

@RestController
public class SysTableService implements SysTableFeign {
	
	@Autowired
	private SysTableMapper sysTableMapper;
	
	@Autowired
	private SysTableActionMapper sysTableActionMapper;
	
	@Autowired
	private SysTableColumnMapper sysTableColumnMapper;

	@Override
	public ModelResult<PageResult<SysTableResponse>> getTableByPage(SysTablePageRequest sysTableRequest) {
		return Result.build(sysTableRequest.executePage(sysTableMapper));
	}

	@Override
	@Transactional
	public ModelResult<SysTableResponse> addTable(SysTableAddRequest sysTableRequest) {
		SysTable sysTable = new SysTable();
		BeanUtil.copyProperties(sysTableRequest, sysTable, CopyOptions.create().setIgnoreError(true));
		sysTableMapper.insertSelective(sysTable);
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableResponse> updateTable(SysTableUpdateRequest sysTableRequest) {
		SysTable sysTable = new SysTable();
		BeanUtil.copyProperties(sysTableRequest, sysTable, CopyOptions.create().setIgnoreError(true));
		sysTableMapper.updateByPrimaryKeySelective(sysTable);
		sysTable = sysTableMapper.selectByPrimaryKey(sysTable.getTableId());
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableResponse> deleteTable(Integer tableId) {
		SysTable sysTable = sysTableMapper.selectByPrimaryKey(tableId);
		sysTableMapper.deleteByPrimaryKey(tableId);
		sysTableActionMapper.deleteByExample(ExampleBuilder.create(SysTableAction.class).andEqualTo("tableId", tableId).build());
		sysTableColumnMapper.deleteByExample(ExampleBuilder.create(SysTableColumn.class).andEqualTo("tableId", tableId).build());
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableActionResponse>> getActionByPage(SysTableActionPageRequest sysActionRequest) {
		return Result.build(sysActionRequest.executePage(sysTableActionMapper));
	}
	
	@Override
	public ModelResult<SysTableActionResponse> getActionById(Integer actionId) {
		SysTableAction sysTableAction = sysTableActionMapper.selectByPrimaryKey(actionId);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableActionResponse> addAction(SysTableActionAddRequest sysActionRequest) {
		SysTableAction sysTableAction = new SysTableAction();
		BeanUtil.copyProperties(sysActionRequest, sysTableAction, CopyOptions.create().setIgnoreError(true));
		sysTableActionMapper.insertSelective(sysTableAction);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableActionResponse> updateAction(SysTableActionUpdateRequest sysActionRequest) {
		SysTableAction sysTableAction = new SysTableAction();
		BeanUtil.copyProperties(sysActionRequest, sysTableAction, CopyOptions.create().setIgnoreError(true));
		sysTableActionMapper.updateByPrimaryKeySelective(sysTableAction);
		sysTableAction = sysTableActionMapper.selectByPrimaryKey(sysTableAction.getTableId());
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableActionResponse> deleteAction(Integer actionId) {
		SysTableAction sysTableAction = sysTableActionMapper.selectByPrimaryKey(actionId);
		sysTableActionMapper.deleteByPrimaryKey(actionId);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableColumnResponse>> getColumnByPage(SysTableColumnPageRequest sysColumnRequest) {
		return Result.build(sysColumnRequest.executePage(sysTableColumnMapper));
	}

	@Override
	@Transactional
	public ModelResult<SysTableColumnResponse> addColumn(SysTableColumnAddRequest sysColumnRequest) {
		SysTableColumn sysTableColumn = new SysTableColumn();
		BeanUtil.copyProperties(sysColumnRequest, sysTableColumn, CopyOptions.create().setIgnoreError(true));
		sysTableColumnMapper.insertSelective(sysTableColumn);
		SysTableColumnResponse sysTableColumnResponse = new SysTableColumnResponse();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableColumnResponse> updateColumn(SysTableColumnUpdateRequest sysColumnRequest) {
		SysTableColumn sysTableColumn = new SysTableColumn();
		BeanUtil.copyProperties(sysColumnRequest, sysTableColumn, CopyOptions.create().setIgnoreError(true));
		sysTableColumnMapper.updateByPrimaryKeySelective(sysTableColumn);
		sysTableColumn = sysTableColumnMapper.selectByPrimaryKey(sysTableColumn.getTableId());
		SysTableColumnResponse sysTableColumnResponse = new SysTableColumnResponse();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableColumnResponse> deleteColumn(Integer columnId) {
		SysTableColumn sysTableColumn = sysTableColumnMapper.selectByPrimaryKey(columnId);
		sysTableColumnMapper.deleteByPrimaryKey(columnId);
		SysTableColumnResponse sysTableColumnResponse = new SysTableColumnResponse();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	public ModelResult<List<SysMenuTreeResponse>> getTableActionTree() {
		List<SysMenuTreeResponse> sysMenuTreeResponses = new ArrayList<SysMenuTreeResponse>();
		List<SysTable> sysTables = sysTableMapper.selectAll();
		List<SysTableAction> sysTableActions = sysTableActionMapper.selectAll();
		sysTableActions.forEach(sysTableAction -> {
			SysTable table = sysTables.stream().filter(
					sysTable -> sysTable.getTableId().equals(sysTableAction.getTableId())).findFirst().get();
			SysMenuTreeResponse sysMenuTreeResponse = new SysMenuTreeResponse();
			sysMenuTreeResponse.setId(sysTableAction.getActionId()*-1);
			sysMenuTreeResponse.setPId(table.getMenuId());
			sysMenuTreeResponse.setName(table.getTableName() + "-" + sysTableAction.getActionName());
			sysMenuTreeResponses.add(sysMenuTreeResponse);
		});
		return Result.build(sysMenuTreeResponses);
	}
}
