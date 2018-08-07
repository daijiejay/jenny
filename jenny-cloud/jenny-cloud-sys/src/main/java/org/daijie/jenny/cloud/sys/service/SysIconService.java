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
import org.daijie.jenny.common.feign.sys.SysIconFeign;
import org.daijie.jenny.common.feign.sys.request.SysIconAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysIconResponse;
import org.daijie.jenny.common.mapper.sys.SysIconMapper;
import org.daijie.jenny.common.model.sys.SysIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysIconService implements SysIconFeign {
	
	@Autowired
	private SysIconMapper sysIconMapper;

	@Override
	public ModelResult<PageResult<SysIconResponse>> getIcon(SysIconPageRequest sysIconPageRequest) {
		PageHelper.startPage(sysIconPageRequest.getPageNumber(), sysIconPageRequest.getPageSize());
		List<SysIcon> icons = sysIconMapper.selectByExample(sysIconPageRequest.exampleBuild(SysIcon.class));
        PageInfo<SysIcon> pageInfo = new PageInfo<>(icons);
		return Result.build(new PageResult<SysIconResponse>(pageInfo.getList(), pageInfo.getTotal(), SysIconResponse.class));
	}
	
	@Override
	public ModelResult<List<SysIconResponse>> getIconAll() {
		List<SysIcon> icons = sysIconMapper.selectAll();
		PageInfo<SysIcon> pageInfo = new PageInfo<>(icons);
		return Result.build(new PageResult<SysIconResponse>(pageInfo.getList(), pageInfo.getTotal(), SysIconResponse.class).getRows());
	}

	@Override
	@Transactional
	public ModelResult<SysIconResponse> addIcon(SysIconAddRequest sysIconRequest) {
		if (sysIconMapper.selectByExample(ExampleBuilder.create(SysIcon.class).andEqualTo("iconCode", sysIconRequest.getIconCode()).build()).size() > 0) {
			throw new ApiException(ResultCode.CODE_100, sysIconRequest.getIconCode()+"图标代码已存在");
		}
		SysIcon sysIcon = new SysIcon();
		BeanUtil.copyProperties(sysIconRequest, sysIcon);
		sysIconMapper.insertSelective(sysIcon);
		SysIconResponse sysIconResponse = new SysIconResponse();
		BeanUtil.copyProperties(sysIcon, sysIconResponse);
		return Result.build(sysIconResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysIconResponse> updateIcon(SysIconUpdateRequest sysIconRequest) {
		if (sysIconMapper.selectByExample(ExampleBuilder.create(SysIcon.class)
				.andEqualTo("iconCode", sysIconRequest.getIconCode())
				.andNotEqualTo("iconId", sysIconRequest.getIconId())
				.build()).size() > 0) {
			throw new ApiException(ResultCode.CODE_100, sysIconRequest.getIconCode()+"图标代码已存在");
		}
		SysIcon sysIcon = new SysIcon();
		BeanUtil.copyProperties(sysIconRequest, sysIcon);
		if (sysIcon.getIconId() != null) {
			sysIconMapper.updateByPrimaryKeySelective(sysIcon);
		} else {
			return Result.build("缺少参数iconId，更新失败！", ApiResult.ERROR, ResultCode.CODE_102);
		}
		SysIconResponse sysIconResponse = new SysIconResponse();
		BeanUtil.copyProperties(sysIcon, sysIconResponse);
		return Result.build(sysIconResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysIconResponse> deleteIcon(Integer IconId) {
		SysIcon sysIcon = sysIconMapper.selectByPrimaryKey(IconId);
		if (sysIcon != null) {
			sysIcon.setIconId(IconId);
			sysIconMapper.updateByPrimaryKey(sysIcon);
			SysIconResponse sysIconResponse = new SysIconResponse();
			BeanUtil.copyProperties(sysIcon, sysIconResponse);
			return Result.build(sysIconResponse);
		}
		return Result.build("无效的图标编号！", ApiResult.ERROR, ResultCode.CODE_102);
	}

}
