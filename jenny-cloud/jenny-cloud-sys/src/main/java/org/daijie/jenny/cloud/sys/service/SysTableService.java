package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
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
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.jenny.common.mapper.sys.SysTableActionMapper;
import org.daijie.jenny.common.mapper.sys.SysTableColumnMapper;
import org.daijie.jenny.common.mapper.sys.SysTableMapper;
import org.daijie.jenny.common.model.sys.SysTable;
import org.daijie.jenny.common.model.sys.SysTableAction;
import org.daijie.jenny.common.model.sys.SysTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		PageHelper.startPage(sysTableRequest.getPageNumber(), sysTableRequest.getPageSize());
		List<SysTable> tables = sysTableMapper.selectByExample(sysTableRequest.exampleBuild(SysTable.class));
        PageInfo<SysTable> pageInfo = new PageInfo<>(tables);
		return Result.build(new PageResult<SysTableResponse>(pageInfo.getList(), pageInfo.getTotal(), SysTableResponse.class));
	}

	@Override
	public ModelResult<SysTableResponse> addTable(SysTableAddRequest sysTableRequest) {
		SysTable sysTable = new SysTable();
		BeanUtil.copyProperties(sysTableRequest, sysTable, CopyOptions.create().setIgnoreError(true));
		sysTableMapper.insertSelective(sysTable);
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
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
	public ModelResult<SysTableResponse> deleteTable(Integer tableId) {
		SysTable sysTable = sysTableMapper.selectByPrimaryKey(tableId);
		sysTableMapper.deleteByPrimaryKey(tableId);
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableActionResponse>> getActionByPage(SysTableActionPageRequest sysActionRequest) {
		PageHelper.startPage(sysActionRequest.getPageNumber(), sysActionRequest.getPageSize());
		List<SysTableAction> actions = sysTableActionMapper.selectByExample(sysActionRequest.exampleBuild(SysTableAction.class));
        PageInfo<SysTableAction> pageInfo = new PageInfo<>(actions);
		return Result.build(new PageResult<SysTableActionResponse>(pageInfo.getList(), pageInfo.getTotal(), SysTableActionResponse.class));
	}

	@Override
	public ModelResult<SysTableActionResponse> addAction(SysTableActionAddRequest sysActionRequest) {
		SysTableAction sysTableAction = new SysTableAction();
		BeanUtil.copyProperties(sysActionRequest, sysTableAction, CopyOptions.create().setIgnoreError(true));
		sysTableActionMapper.insertSelective(sysTableAction);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
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
	public ModelResult<SysTableActionResponse> deleteAction(Integer actionId) {
		SysTableAction sysTableAction = sysTableActionMapper.selectByPrimaryKey(actionId);
		sysTableActionMapper.deleteByPrimaryKey(actionId);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableActionResponse>> getColumnByPage(SysTableColumnPageRequest sysColumnRequest) {
		PageHelper.startPage(sysColumnRequest.getPageNumber(), sysColumnRequest.getPageSize());
		List<SysTableColumn> columns = sysTableColumnMapper.selectByExample(sysColumnRequest.exampleBuild(SysTableColumn.class));
        PageInfo<SysTableColumn> pageInfo = new PageInfo<>(columns);
		return Result.build(new PageResult<SysTableActionResponse>(pageInfo.getList(), pageInfo.getTotal(), SysTableActionResponse.class));
	}

	@Override
	public ModelResult<SysTableColumnPageRequest> addColumn(SysTableColumnAddRequest sysColumnRequest) {
		SysTableColumn sysTableColumn = new SysTableColumn();
		BeanUtil.copyProperties(sysColumnRequest, sysTableColumn, CopyOptions.create().setIgnoreError(true));
		sysTableColumnMapper.insertSelective(sysTableColumn);
		SysTableColumnPageRequest sysTableColumnResponse = new SysTableColumnPageRequest();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	public ModelResult<SysTableColumnPageRequest> updateColumn(SysTableColumnUpdateRequest sysColumnRequest) {
		SysTableColumn sysTableColumn = new SysTableColumn();
		BeanUtil.copyProperties(sysColumnRequest, sysTableColumn, CopyOptions.create().setIgnoreError(true));
		sysTableColumnMapper.updateByPrimaryKeySelective(sysTableColumn);
		sysTableColumn = sysTableColumnMapper.selectByPrimaryKey(sysTableColumn.getTableId());
		SysTableColumnPageRequest sysTableColumnResponse = new SysTableColumnPageRequest();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	public ModelResult<SysTableColumnPageRequest> deleteColumn(Integer columnId) {
		SysTableColumn sysTableColumn = sysTableColumnMapper.selectByPrimaryKey(columnId);
		sysTableColumnMapper.deleteByPrimaryKey(columnId);
		SysTableColumnPageRequest sysTableColumnResponse = new SysTableColumnPageRequest();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}
}
