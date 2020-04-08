package org.daijie.jenny.api.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.SysTableFeign;
import org.daijie.jenny.common.feign.sys.request.*;
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableColumnResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public ModelResult<SysTableResponse> addTable(@RequestBody SysTableAddRequest sysTableRequest) {
		return sysTableFeign.addTable(sysTableRequest);
	}

	@ApiOperation(value = "更新表格")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysTableResponse> updateTable(@RequestBody SysTableUpdateRequest sysTableRequest) {
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
	public ModelResult<SysTableActionResponse> addAction(@RequestBody SysTableActionAddRequest sysActionRequest) {
		return sysTableFeign.addAction(sysActionRequest);
	}
	
	@ApiOperation(value = "更新表格操作功能")
	@RequestMapping(value = "/action/update", method = RequestMethod.PUT)
	public ModelResult<SysTableActionResponse> updateAction(@RequestBody SysTableActionUpdateRequest sysActionRequest) {
		return sysTableFeign.updateAction(sysActionRequest);
	}
	
	@ApiOperation(value = "删除表格操作功能")
	@RequestMapping(value = "/action/delete/{actionId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableActionResponse> deleteAction(@PathVariable(name = "actionId") Integer actionId) {
		return sysTableFeign.deleteAction(actionId);
	}
	
	@ApiOperation(value = "条件查询表格列字段")
	@RequestMapping(value = "/column/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysTableColumnResponse>> getColumnByPage(SysTableColumnPageRequest sysColumnRequest) {
		return sysTableFeign.getColumnByPage(sysColumnRequest);
	}
	
	@ApiOperation(value = "添加表格列字段")
	@RequestMapping(value = "/column/add", method = RequestMethod.POST)
	public ModelResult<SysTableColumnResponse> addColumn(@RequestBody SysTableColumnAddRequest sysColumnRequest) {
		return sysTableFeign.addColumn(sysColumnRequest);
	}
	
	@ApiOperation(value = "更新表格列字段")
	@RequestMapping(value = "/column/update", method = RequestMethod.PUT)
	public ModelResult<SysTableColumnResponse> updateColumn(@RequestBody SysTableColumnUpdateRequest sysColumnRequest) {
		return sysTableFeign.updateColumn(sysColumnRequest);
	}
	
	@ApiOperation(value = "删除表格列字段")
	@RequestMapping(value = "/column/delete/{columnId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableColumnResponse> deleteColumn(@PathVariable(name = "columnId") Integer columnId) {
		return sysTableFeign.deleteColumn(columnId);
	}
}
