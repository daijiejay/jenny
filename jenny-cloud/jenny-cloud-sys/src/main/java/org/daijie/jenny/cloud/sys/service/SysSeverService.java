package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.controller.exception.ApiException;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysServerFeign;
import org.daijie.jenny.common.feign.sys.request.SysServerAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysServerUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.jenny.common.mapper.sys.SysServerMapper;
import org.daijie.jenny.common.model.sys.SysServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysSeverService implements SysServerFeign {
	
	@Autowired
	private SysServerMapper sysServerMapper;

	@Override
	public ModelResult<PageResult<SysServerResponse>> getServer(SysServerPageRequest sysServerPageRequest) {
		return Result.build(sysServerPageRequest.executePage(sysServerMapper));
	}
	
	@Override
	public ModelResult<List<SysServerResponse>> getServerAll() {
		List<SysServer> Servers = sysServerMapper.selectAll();
		PageInfo<SysServer> pageInfo = new PageInfo<>(Servers);
		return Result.build(new PageResult<SysServerResponse>(pageInfo.getList(), pageInfo.getTotal(), SysServerResponse.class).getRows());
	}

	@Override
	@Transactional
	public ModelResult<SysServerResponse> addServer(SysServerAddRequest sysServerRequest) {
		if (sysServerMapper.selectByExample(ExampleBuilder.create(SysServer.class).andEqualTo("serverId", sysServerRequest.getServerId()).build()).size() > 0) {
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
			sysServerMapper.updateByPrimaryKeySelective(sysServer);
		} else {
			return Result.build("缺少参数serverId，更新失败！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysServerResponse sysServerResponse = new SysServerResponse();
		BeanUtil.copyProperties(sysServer, sysServerResponse);
		return Result.build(sysServerResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysServerResponse> deleteServer(Integer ServerId) {
		SysServer sysServer = sysServerMapper.selectByPrimaryKey(ServerId);
		if (sysServer != null) {
			sysServerMapper.deleteByPrimaryKey(sysServer);
			SysServerResponse sysServerResponse = new SysServerResponse();
			BeanUtil.copyProperties(sysServer, sysServerResponse);
			return Result.build(sysServerResponse);
		}
		return Result.build("无效的图标编号！", ApiResult.ERROR, ResultCode.CODE_102);
	}

}
