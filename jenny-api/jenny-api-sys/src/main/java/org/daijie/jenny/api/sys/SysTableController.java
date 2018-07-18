package org.daijie.jenny.api.sys;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单表格权限管理")
@RestController
@RequestMapping(value = "systable")
public class SysTableController {

	@Autowired
	private SysTableFeign sysTableFeign;

	@ApiOperation(value = "条件查询表格")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysTableResponse>> getTableByPage(SysTablePageRequest sysTableRequest) {
		return sysTableFeign.getTableByPage(sysTableRequest);
	}

	@ApiOperation(value = "添加表格")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysTableResponse> addTable(SysTableAddRequest sysTableRequest) {
		return sysTableFeign.addTable(sysTableRequest);
	}

	@ApiOperation(value = "更新表格")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysTableResponse> updateTable(SysTableUpdateRequest sysTableRequest) {
		return sysTableFeign.updateTable(sysTableRequest);
	}

	@ApiOperation(value = "删除表格")
	@RequestMapping(value = "/delete/{tableId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableResponse> deleteTable(@PathVariable(name = "tableId") Integer tableId) {
		return sysTableFeign.deleteTable(tableId);
	}
	
	@ApiOperation(value = "条件查询表格操作功能")
	@RequestMapping(value = "/action/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysTableActionResponse>> getActionByPage(SysTableActionPageRequest sysActionRequest) {
		return sysTableFeign.getActionByPage(sysActionRequest);
	}
	
	@ApiOperation(value = "添加表格操作功能")
	@RequestMapping(value = "/action/add", method = RequestMethod.POST)
	public ModelResult<SysTableActionResponse> addAction(SysTableActionAddRequest sysActionRequest) {
		return sysTableFeign.addAction(sysActionRequest);
	}
	
	@ApiOperation(value = "更新表格操作功能")
	@RequestMapping(value = "/action/update", method = RequestMethod.PUT)
	public ModelResult<SysTableActionResponse> updateAction(SysTableActionUpdateRequest sysActionRequest) {
		return sysTableFeign.updateAction(sysActionRequest);
	}
	
	@ApiOperation(value = "删除表格操作功能")
	@RequestMapping(value = "/action/delete/{actionId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableActionResponse> deleteAction(@PathVariable(name = "actionId") Integer actionId) {
		return sysTableFeign.deleteAction(actionId);
	}
	
	@ApiOperation(value = "条件查询表格列字段")
	@RequestMapping(value = "/column/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysTableColumnPageRequest>> getColumnByPage(SysTableColumnPageRequest sysColumnRequest) {
		return sysTableFeign.getColumnByPage(sysColumnRequest);
	}
	
	@ApiOperation(value = "添加表格列字段")
	@RequestMapping(value = "/column/add", method = RequestMethod.POST)
	public ModelResult<SysTableColumnPageRequest> addColumn(SysTableColumnAddRequest sysColumnRequest) {
		return sysTableFeign.addColumn(sysColumnRequest);
	}
	
	@ApiOperation(value = "更新表格列字段")
	@RequestMapping(value = "/column/update", method = RequestMethod.PUT)
	public ModelResult<SysTableColumnPageRequest> updateColumn(SysTableColumnUpdateRequest sysColumnRequest) {
		return sysTableFeign.updateColumn(sysColumnRequest);
	}
	
	@ApiOperation(value = "删除表格列字段")
	@RequestMapping(value = "/column/delete/{columnId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableColumnPageRequest> deleteColumn(@PathVariable(name = "columnId") Integer columnId) {
		return sysTableFeign.deleteColumn(columnId);
	}
}
