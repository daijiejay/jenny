package org.daijie.jenny.cloud.sys.service;

import java.util.List;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysMenuFeign;
import org.daijie.jenny.common.feign.sys.request.SysMenuAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysMenuService implements SysMenuFeign {
	
	private SysMenuMapper sysMenuMapper;

	@Override
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest) {
		PageHelper.startPage(sysMenuPageRequest.getPageNumber(), sysMenuPageRequest.getPageSize());
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuPageRequest, sysMenu);
		List<SysMenu> roles = sysMenuMapper.select(sysMenu);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(roles);
		return Result.build(new PageResult<SysMenuResponse>(pageInfo.getList(), pageInfo.getTotal(), SysMenuResponse.class));
	}

	@Override
	public ModelResult<SysMenuResponse> addMenu(SysMenuAddRequest sysMenuRequest) {
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuRequest, sysMenu);
		sysMenuMapper.insert(sysMenu);
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}

	@Override
	public ModelResult<SysMenuResponse> updateMenu(SysMenuUpdateRequest sysMenuRequest) {
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuRequest, sysMenu);
		sysMenuMapper.updateByExampleSelective(sysMenu, ExampleBuilder.create(SysMenu.class).andEqualTo("menuId", sysMenuRequest.getMenuId()).build());
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}

	@Override
	public ModelResult<SysMenuResponse> deleteMenu(@PathVariable(name = "menuId") Integer menuId) {
		List<SysMenu> list = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("menuId", menuId).build());
		if (list.size() == 1) {
			sysMenuMapper.deleteByPrimaryKey(list.get(0).getMenuId());
			SysMenuResponse sysMenuResponse = new SysMenuResponse();
			BeanUtil.copyProperties(list.get(0), sysMenuResponse);
			return Result.build(sysMenuResponse);
		}
		return Result.build("无效的菜单编号！", ApiResult.ERROR, ResultCode.CODE_102);
	}

}
