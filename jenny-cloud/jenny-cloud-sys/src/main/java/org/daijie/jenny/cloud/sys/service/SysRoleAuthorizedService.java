package org.daijie.jenny.cloud.sys.service;

import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.cloud.sys.mapper.SysRoleManagerMapper;
import org.daijie.jenny.common.feign.sys.SysRoleAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.request.SysRoleAuthorizedPageRequest;
import org.daijie.jenny.common.feign.sys.response.SysRoleAuthorizedReponse;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysRoleAuthorizedService implements SysRoleAuthorizedFeign {
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysRoleManagerMapper sysRoleManagerMapper;

	@Override
	public ModelResult<PageResult<SysRoleAuthorizedReponse>> getRoleAuthorizedAll(
			SysRoleAuthorizedPageRequest sysRoleAuthorizedRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysRoleAuthorizedRequest.getRoleName()), wrapper -> wrapper.andEqualTo("roleName", sysRoleAuthorizedRequest.getRoleName()))
				.and(sysRoleAuthorizedRequest.getRoleId() != null, wrapper -> wrapper.andEqualTo("roleId", sysRoleAuthorizedRequest.getRoleId()))
				.and(sysRoleAuthorizedRequest.getUserId() != null, wrapper -> wrapper.andEqualTo("userId", sysRoleAuthorizedRequest.getUserId()))
				.page(sysRoleAuthorizedRequest.getPageNumber(), sysRoleAuthorizedRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysRoleAuthorized> page = this.sysRoleAuthorizedMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysRoleAuthorizedReponse>(page.getRows(), page.getTotal(), SysRoleAuthorizedReponse.class));
	}

	@Override
	public ModelResult<List<String>> getRolesByUser(Integer userId) {
		List<String> roles = sysRoleManagerMapper.selectRolesByUser(userId);
		return Result.build(roles);
	}

}
