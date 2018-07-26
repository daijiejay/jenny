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
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class SysRoleAuthorizedService implements SysRoleAuthorizedFeign {
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysRoleManagerMapper sysRoleManagerMapper;

	@Override
	public ModelResult<PageResult<SysRoleAuthorizedReponse>> getRoleAuthorizedAll(
			SysRoleAuthorizedPageRequest sysRoleAuthorizedRequest) {
		PageHelper.startPage(sysRoleAuthorizedRequest.getPageNumber(), sysRoleAuthorizedRequest.getPageSize());
		List<SysRoleAuthorized> roleAuthorizeds = sysRoleAuthorizedMapper.selectByExample(sysRoleAuthorizedRequest.exampleBuild(SysRoleAuthorized.class));
        PageInfo<SysRoleAuthorized> pageInfo = new PageInfo<>(roleAuthorizeds);
		return Result.build(new PageResult<SysRoleAuthorizedReponse>(pageInfo.getList(), pageInfo.getTotal(), SysRoleAuthorizedReponse.class));
	}

	@Override
	public ModelResult<List<String>> getRolesByUser(Integer userId) {
		List<String> roles = sysRoleManagerMapper.selectRolesByUser(userId);
		return Result.build(roles);
	}

}
