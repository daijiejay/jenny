package org.daijie.jenny.common.feign.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.request.SysRoleAuthorizedPageRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleAuthorizedReponse;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(description="系统角色权限授权管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/role/authorized")
public interface SysRoleAuthorizedFeign {
	
	@ApiOperation(notes = "条件分页查询角色", value = "条件分页查询角色")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysRoleAuthorizedReponse>> getRoleAuthorizedAll(SysRoleAuthorizedPageRequest sysRoleAuthorizedRequest);
	
	@ApiOperation(value = "根据用户编号查询角色")
	@RequestMapping(value = "/query/user/{userId}", method = RequestMethod.GET)
	public ModelResult<List<String>> getRolesByUser(@PathVariable(name = "userId") Integer userId);
}
