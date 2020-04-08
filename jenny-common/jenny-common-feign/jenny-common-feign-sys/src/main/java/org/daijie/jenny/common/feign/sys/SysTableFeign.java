package org.daijie.jenny.common.feign.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.request.*;
import org.daijie.jenny.common.feign.sys.response.SysMenuTreeResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableActionResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableColumnResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableResponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(description="系统表格管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/systable")
public interface SysTableFeign {

	@ApiOperation(value = "条件查询表格")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysTableResponse>> getTableByPage(@RequestBody SysTablePageRequest sysTableRequest);
	
	@ApiOperation(value = "添加表格")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysTableResponse> addTable(@RequestBody SysTableAddRequest sysTableRequest);
	
	@ApiOperation(value = "更新表格")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysTableResponse> updateTable(@RequestBody SysTableUpdateRequest sysTableRequest);
	
	@ApiOperation(value = "删除表格")
	@RequestMapping(value = "/delete/{tableId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableResponse> deleteTable(@PathVariable(name = "tableId") Integer tableId);
	
	@ApiOperation(value = "条件查询表格操作功能")
	@RequestMapping(value = "/action/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysTableActionResponse>> getActionByPage(@RequestBody SysTableActionPageRequest sysActionRequest);
	
	@ApiOperation(value = "功能功能编号查询")
	@RequestMapping(value = "/action/query/{actionId}", method = RequestMethod.GET)
	public ModelResult<SysTableActionResponse> getActionById(@PathVariable(name = "actionId") Integer actionId);
	
	@ApiOperation(value = "添加表格操作功能")
	@RequestMapping(value = "/action/add", method = RequestMethod.POST)
	public ModelResult<SysTableActionResponse> addAction(@RequestBody SysTableActionAddRequest sysActionRequest);
	
	@ApiOperation(value = "更新表格操作功能")
	@RequestMapping(value = "/action/update", method = RequestMethod.PUT)
	public ModelResult<SysTableActionResponse> updateAction(@RequestBody SysTableActionUpdateRequest sysActionRequest);
	
	@ApiOperation(value = "删除表格操作功能")
	@RequestMapping(value = "/action/delete/{actionId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableActionResponse> deleteAction(@PathVariable(name = "actionId") Integer actionId);
	
	@ApiOperation(value = "条件查询表格列字段")
	@RequestMapping(value = "/column/query", method = RequestMethod.POST)
	public ModelResult<PageResult<SysTableColumnResponse>> getColumnByPage(@RequestBody SysTableColumnPageRequest sysColumnRequest);
	
	@ApiOperation(value = "添加表格列字段")
	@RequestMapping(value = "/column/add", method = RequestMethod.POST)
	public ModelResult<SysTableColumnResponse> addColumn(@RequestBody SysTableColumnAddRequest sysColumnRequest);
	
	@ApiOperation(value = "更新表格列字段")
	@RequestMapping(value = "/column/update", method = RequestMethod.PUT)
	public ModelResult<SysTableColumnResponse> updateColumn(@RequestBody SysTableColumnUpdateRequest sysColumnRequest);
	
	@ApiOperation(value = "删除表格列字段")
	@RequestMapping(value = "/column/delete/{columnId}", method = RequestMethod.DELETE)
	public ModelResult<SysTableColumnResponse> deleteColumn(@PathVariable(name = "columnId") Integer columnId);
	
	@ApiOperation(value = "获取表格功能树")
	@RequestMapping(value = "/action/query/tree", method = RequestMethod.GET)
	public ModelResult<List<SysMenuTreeResponse>> getTableActionTree();
}
