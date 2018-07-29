package org.daijie.jenny.common.feign.sys;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysTableAuthorizedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统菜单权限授权管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/menu/authorized")
public interface SysMenuAuthorizedFeign {
	
	@ApiOperation(value = "获取所有菜单")
	@RequestMapping(value = "/query/all/{userId}", method = RequestMethod.GET)
	public ModelResult<List<SysMenuResponse>> getMenuAuthrozied(@PathVariable(name = "userId") Integer userId);
	
	@ApiOperation(value = "根据菜单编号获取表格信息")
	@RequestMapping(value = "/query/table/{menuId}", method = RequestMethod.GET)
	public ModelResult<SysTableAuthorizedResponse> getActionByMenu(@PathVariable(name = "menuId") Integer menuId);
}
