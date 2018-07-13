package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.sys.SysActionFeign;
import org.daijie.jenny.common.feign.sys.request.SysActionAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysActionUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysActionResponse;
import org.daijie.jenny.common.mapper.sys.SysActionMapper;
import org.daijie.jenny.common.model.sys.SysAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

@RestController
public class SysActionService implements SysActionFeign {
	
	@Autowired
	private SysActionMapper sysActionMapper;

	@Override
	public ModelResult<PageResult<SysActionResponse>> getActionByPage(SysActionPageRequest sysActionRequest) {
		PageHelper.startPage(sysActionRequest.getPageNumber(), sysActionRequest.getPageSize());
		List<SysAction> actions = sysActionMapper.selectByExample(sysActionRequest.exampleBuild(SysAction.class));
        PageInfo<SysAction> pageInfo = new PageInfo<>(actions);
		return Result.build(new PageResult<SysActionResponse>(pageInfo.getList(), pageInfo.getTotal(), SysActionResponse.class));
	}

	@Override
	public ModelResult<SysActionResponse> addAction(SysActionAddRequest sysActionRequest) {
		SysAction sysAction = new SysAction();
		BeanUtil.copyProperties(sysActionRequest, sysAction, CopyOptions.create().setIgnoreError(true));
		sysActionMapper.insertSelective(sysAction);
		SysActionResponse sysActionResponse = new SysActionResponse();
		BeanUtil.copyProperties(sysAction, sysActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysActionResponse);
	}

	@Override
	public ModelResult<SysActionResponse> updateAction(SysActionUpdateRequest sysActionRequest) {
		SysAction sysAction = new SysAction();
		BeanUtil.copyProperties(sysActionRequest, sysAction, CopyOptions.create().setIgnoreError(true));
		sysActionMapper.updateByPrimaryKeySelective(sysAction);
		SysActionResponse sysActionResponse = new SysActionResponse();
		BeanUtil.copyProperties(sysAction, sysActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysActionResponse);
	}

	@Override
	public ModelResult<SysActionResponse> deleteAction(Integer actionId) {
		SysAction sysAction = sysActionMapper.selectByPrimaryKey(actionId);
		if (sysAction == null) {
			return Result.build("无效的功能编号！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		sysActionMapper.deleteByPrimaryKey(actionId);
		SysActionResponse sysActionResponse = new SysActionResponse();
		BeanUtil.copyProperties(sysAction, sysActionResponse, CopyOptions.create().setIgnoreError(true));
		return Result.build(sysActionResponse);
	}

}
