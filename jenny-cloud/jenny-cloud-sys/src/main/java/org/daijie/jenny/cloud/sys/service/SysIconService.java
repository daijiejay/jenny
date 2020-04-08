package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.common.feign.sys.SysIconFeign;
import org.daijie.jenny.common.feign.sys.request.SysIconAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysIconResponse;
import org.daijie.jenny.common.mapper.sys.SysIconMapper;
import org.daijie.jenny.common.model.sys.SysIcon;
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
public class SysIconService implements SysIconFeign {
	
	@Autowired
	private SysIconMapper sysIconMapper;

	@Override
	public ModelResult<PageResult<SysIconResponse>> getIcon(SysIconPageRequest sysIconPageRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysIconPageRequest.getIconCode()), wrapper -> wrapper.andEqualTo("iconCode", sysIconPageRequest.getIconCode()))
				.and(StringUtils.isNotEmpty(sysIconPageRequest.getIconName()), wrapper -> wrapper.andEqualTo("iconCode", sysIconPageRequest.getIconName()))
				.and(sysIconPageRequest.getIconId() != null, wrapper -> wrapper.andEqualTo("iconId", sysIconPageRequest.getIconId()))
				.page(sysIconPageRequest.getPageNumber(), sysIconPageRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysIcon> page = this.sysIconMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysIconResponse>(page.getRows(), page.getTotal(), SysIconResponse.class));
	}
	
	@Override
	public ModelResult<List<SysIconResponse>> getIconAll() {
		List<SysIcon> icons = this.sysIconMapper.selectAll();
		return Result.build(new PageResult<SysIconResponse>(icons, new Long(icons.size()), SysIconResponse.class).getRows());
	}

	@Override
	@Transactional
	public ModelResult<SysIconResponse> addIcon(SysIconAddRequest sysIconRequest) {
		if (this.sysIconMapper.selectCountByWrapper(Wrapper.newWrapper().andEqualTo("iconCode", sysIconRequest.getIconCode())) > 0) {
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
		if (this.sysIconMapper.selectCountByWrapper(Wrapper.newWrapper()
				.andEqualTo("iconCode", sysIconRequest.getIconCode())
				.andNotEqualTo("iconId", sysIconRequest.getIconId())) > 0) {
			throw new ApiException(ResultCode.CODE_100, sysIconRequest.getIconCode()+"图标代码已存在");
		}
		SysIcon sysIcon = new SysIcon();
		BeanUtil.copyProperties(sysIconRequest, sysIcon);
		if (sysIcon.getIconId() != null) {
			this.sysIconMapper.updateSelectiveById(sysIcon);
		} else {
			return Result.build("缺少参数iconId，更新失败！", Result.ERROR, ResultCode.CODE_102);
		}
		SysIconResponse sysIconResponse = new SysIconResponse();
		BeanUtil.copyProperties(sysIcon, sysIconResponse);
		return Result.build(sysIconResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysIconResponse> deleteIcon(Integer IconId) {
		SysIcon sysIcon = this.sysIconMapper.selectById(IconId);
		if (sysIcon != null) {
			sysIcon.setIconId(IconId);
			sysIconMapper.deleteById(sysIcon);
			SysIconResponse sysIconResponse = new SysIconResponse();
			BeanUtil.copyProperties(sysIcon, sysIconResponse);
			return Result.build(sysIconResponse);
		}
		return Result.build("无效的图标编号！", Result.ERROR, ResultCode.CODE_102);
	}

}
