package org.daijie.jenny.api.sys;

import java.util.List;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.sys.SysIconFeign;
import org.daijie.jenny.common.feign.sys.request.SysIconAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysIconUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysIconPickerResponse;
import org.daijie.jenny.common.feign.sys.response.SysIconResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="系统图标管理")
@RestController
@RequestMapping("sysicon")
public class SysIconController {
	
	@Autowired
	private SysIconFeign sysIconFeign;

	@ApiOperation(value = "条件查询图标")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelResult<PageResult<SysIconResponse>> getIcon(SysIconPageRequest sysIconRequest) {
		return sysIconFeign.getIcon(sysIconRequest);
	}
	
	@ApiOperation(value = "查询所有图标，用于fontIconPicker插件加载")
	@RequestMapping(value = "/query/fontIconPicker", method = RequestMethod.GET)
	public ModelResult<SysIconPickerResponse> fontIconPicker() {
		List<SysIconResponse> icons = sysIconFeign.getIconAll().getData();
		SysIconPickerResponse sysIconPickerResponse = new SysIconPickerResponse();
		icons.forEach(icon -> {
			sysIconPickerResponse.getIconCodes().add(icon.getIconCode());
			sysIconPickerResponse.getIconNames().add(icon.getIconName());
		});
		return Result.build(sysIconPickerResponse);
	}
	
	@ApiOperation(value = "添加图标")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelResult<SysIconResponse> addIcon(SysIconAddRequest sysIconRequest) {
		return sysIconFeign.addIcon(sysIconRequest);
	}

	@ApiOperation(value = "更新图标")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelResult<SysIconResponse> updateIcon(SysIconUpdateRequest sysIconRequest) {
		return sysIconFeign.updateIcon(sysIconRequest);
	}

	@ApiOperation(value = "删除图标")
	@RequestMapping(value = "/delete/{iconId}", method = RequestMethod.DELETE)
	public ModelResult<SysIconResponse> deleteIcon(@PathVariable(name = "iconId") Integer iconId) {
		return sysIconFeign.deleteIcon(iconId);
	}
}
