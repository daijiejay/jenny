package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.cloud.sys.mapper.SysRoleManagerMapper;
import org.daijie.jenny.common.feign.sys.SysRoleAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleAuthorizedPageRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleAuthorizedReponse;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysRoleAuthorizedService implements SysRoleAuthorizedFeign {
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysRoleManagerMapper sysRoleManagerMapper;

	@Override
	public ModelResult<PageResult<SysRoleAuthorizedReponse>> getRoleAuthorizedAll(
			SysRoleAuthorizedPageRequest sysRoleAuthorizedRequest) {
		return Result.build(sysRoleAuthorizedRequest.executePage(sysRoleAuthorizedMapper));
	}

	@Override
	public ModelResult<List<String>> getRolesByUser(Integer userId) {
		List<String> roles = sysRoleManagerMapper.selectRolesByUser(userId);
		return Result.build(roles);
	}

}
