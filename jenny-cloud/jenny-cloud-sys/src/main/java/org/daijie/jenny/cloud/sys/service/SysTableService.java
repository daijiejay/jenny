package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.common.feign.sys.SysTableFeign;
import org.daijie.jenny.common.feign.sys.request.*;
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
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysTableRequest.getInterfaceMethod()), wrapper -> wrapper.andEqualTo("interfaceMethod", sysTableRequest.getInterfaceMethod()))
				.and(StringUtils.isNotEmpty(sysTableRequest.getInterfaceServerId()), wrapper -> wrapper.andEqualTo("interfaceServerId", sysTableRequest.getInterfaceServerId()))
				.and(StringUtils.isNotEmpty(sysTableRequest.getInterfaceUrl()), wrapper -> wrapper.andEqualTo("interfaceUrl", sysTableRequest.getInterfaceUrl()))
				.and(sysTableRequest.getTableId() != null, wrapper -> wrapper.andEqualTo("tableId", sysTableRequest.getTableId()))
				.page(sysTableRequest.getPageNumber(), sysTableRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysTable> page = this.sysTableMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysTableResponse>(page.getRows(), page.getTotal(), SysTableResponse.class));
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
		sysTableMapper.updateSelectiveById(sysTable);
		sysTable = sysTableMapper.selectById(sysTable.getTableId());
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableResponse> deleteTable(Integer tableId) {
		SysTable sysTable = sysTableMapper.selectById(tableId);
		sysTableMapper.deleteById(tableId);
		sysTableActionMapper.deleteByWrapper(Wrapper.newWrapper().andEqualTo("tableId", tableId));
		sysTableColumnMapper.deleteByWrapper(Wrapper.newWrapper().andEqualTo("tableId", tableId));
		SysTableResponse sysTableResponse = new SysTableResponse();
		BeanUtil.copyProperties(sysTable, sysTableResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableActionResponse>> getActionByPage(SysTableActionPageRequest sysActionRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysActionRequest.getActionName()), wrapper -> wrapper.andEqualTo("actionName", sysActionRequest.getActionName()))
				.and(StringUtils.isNotEmpty(sysActionRequest.getFormTarget()), wrapper -> wrapper.andEqualTo("formTarget", sysActionRequest.getFormTarget()))
				.and(sysActionRequest.getActionId() != null, wrapper -> wrapper.andEqualTo("actionId", sysActionRequest.getActionId()))
				.page(sysActionRequest.getPageNumber(), sysActionRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysTableAction> page = this.sysTableActionMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysTableActionResponse>(page.getRows(), page.getTotal(), SysTableActionResponse.class));
	}
	
	@Override
	public ModelResult<SysTableActionResponse> getActionById(Integer actionId) {
		SysTableAction sysTableAction = sysTableActionMapper.selectById(actionId);
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
		sysTableActionMapper.updateSelectiveById(sysTableAction);
		sysTableAction = sysTableActionMapper.selectById(sysTableAction.getTableId());
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableActionResponse> deleteAction(Integer actionId) {
		SysTableAction sysTableAction = sysTableActionMapper.selectById(actionId);
		sysTableActionMapper.deleteById(actionId);
		SysTableActionResponse sysTableActionResponse = new SysTableActionResponse();
		BeanUtil.copyProperties(sysTableAction, sysTableActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableActionResponse);
	}

	@Override
	public ModelResult<PageResult<SysTableColumnResponse>> getColumnByPage(SysTableColumnPageRequest sysColumnRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysColumnRequest.getField()), wrapper -> wrapper.andEqualTo("field", sysColumnRequest.getField()))
				.and(StringUtils.isNotEmpty(sysColumnRequest.getTitle()), wrapper -> wrapper.andEqualTo("title", sysColumnRequest.getTitle()))
				.and(sysColumnRequest.getTableId() != null, wrapper -> wrapper.andEqualTo("tableId", sysColumnRequest.getTableId()))
				.page(sysColumnRequest.getPageNumber(), sysColumnRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysTableColumn> page = this.sysTableColumnMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysTableColumnResponse>(page.getRows(), page.getTotal(), SysTableColumnResponse.class));
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
		sysTableColumnMapper.updateSelectiveById(sysTableColumn);
		sysTableColumn = sysTableColumnMapper.selectById(sysTableColumn.getTableId());
		SysTableColumnResponse sysTableColumnResponse = new SysTableColumnResponse();
		BeanUtil.copyProperties(sysTableColumn, sysTableColumnResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysTableColumnResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysTableColumnResponse> deleteColumn(Integer columnId) {
		SysTableColumn sysTableColumn = sysTableColumnMapper.selectById(columnId);
		sysTableColumnMapper.deleteById(columnId);
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
