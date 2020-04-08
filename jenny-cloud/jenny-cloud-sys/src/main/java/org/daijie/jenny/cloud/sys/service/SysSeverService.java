package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.common.feign.sys.SysServerFeign;
import org.daijie.jenny.common.feign.sys.request.SysServerAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.jenny.common.mapper.sys.SysServerMapper;
import org.daijie.jenny.common.model.sys.SysServer;
import org.daijie.swagger.exception.ApiException;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class SysSeverService implements SysServerFeign {
	
	@Autowired
	private SysServerMapper sysServerMapper;

	@Override
	public ModelResult<PageResult<SysServerResponse>> getServer(SysServerPageRequest sysServerPageRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysServerPageRequest.getServerAddr()), wrapper -> wrapper.andEqualTo("serverAddr", sysServerPageRequest.getServerAddr()))
				.and(StringUtils.isNotEmpty(sysServerPageRequest.getServerId()), wrapper -> wrapper.andEqualTo("serverId", sysServerPageRequest.getServerId()))
				.page(sysServerPageRequest.getPageNumber(), sysServerPageRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysServer> page = this.sysServerMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysServerResponse>(page.getRows(), page.getTotal(), SysServerResponse.class));
	}
	
	@Override
	public ModelResult<List<SysServerResponse>> getServerAll() {
		List<SysServer> Servers = sysServerMapper.selectAll();
		return Result.build(new PageResult<SysServerResponse>(Servers, new Long(Servers.size()), SysServerResponse.class).getRows());
	}

	@Override
	@Transactional
	public ModelResult<SysServerResponse> addServer(SysServerAddRequest sysServerRequest) {
		if (sysServerMapper.selectCountByWrapper(Wrapper.newWrapper().andEqualTo("serverId", sysServerRequest.getServerId())) > 0) {
			throw new ApiException(ResultCode.CODE_100, sysServerRequest.getServerId()+"服务名称已存在");
		}
		SysServer sysServer = new SysServer();
		BeanUtil.copyProperties(sysServerRequest, sysServer);
		sysServerMapper.insertSelective(sysServer);
		SysServerResponse sysServerResponse = new SysServerResponse();
		BeanUtil.copyProperties(sysServer, sysServerResponse);
		return Result.build(sysServerResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysServerResponse> updateServer(SysServerUpdateRequest sysServerRequest) {
		SysServer sysServer = new SysServer();
		BeanUtil.copyProperties(sysServerRequest, sysServer);
		if (sysServer.getServerId() != null) {
			sysServerMapper.updateSelectiveById(sysServer);
		} else {
			return Result.build("缺少参数serverId，更新失败！", Result.ERROR, ResultCode.CODE_102);
		}
		SysServerResponse sysServerResponse = new SysServerResponse();
		BeanUtil.copyProperties(sysServer, sysServerResponse);
		return Result.build(sysServerResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysServerResponse> deleteServer(Integer ServerId) {
		SysServer sysServer = sysServerMapper.selectById(ServerId);
		if (sysServer != null) {
			sysServerMapper.deleteById(sysServer);
			SysServerResponse sysServerResponse = new SysServerResponse();
			BeanUtil.copyProperties(sysServer, sysServerResponse);
			return Result.build(sysServerResponse);
		}
		return Result.build("无效的图标编号！", Result.ERROR, ResultCode.CODE_102);
	}

}
