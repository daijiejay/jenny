package org.daijie.jenny.api.sys;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.result.ApiResult;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserResponse;
import org.daijie.shiro.authc.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="登录管理")
@RestController
public class SysLoginController {

	@Autowired
	private SysUserFeign sysUserFeign;

	@ApiOperation(notes = "登录", value = "登录")
	@RequestMapping(value = "/syslogin", method = RequestMethod.POST)
	public ModelResult<SysUserResponse> login(
			@ApiParam(value="用户名") @RequestParam String username, 
			@ApiParam(value="密码") @RequestParam String password) {
		SysUserResponse sysUserResponse = sysUserFeign.getUserByUsername(username).getData();
		if (sysUserResponse != null) {
			SysUserPasswordResponse passwordResponse = sysUserFeign.getUserPasswordById(sysUserResponse.getId()).getData();
			Auth.login(username, password, passwordResponse.getSalt(), passwordResponse.getPassword(), sysUserResponse);
			return Result.build(sysUserResponse, "登录成功", ApiResult.SUCCESS);
		}
		return Result.build("用户名或密码错误！", ApiResult.ERROR, ResultCode.CODE_100);
	}
	
	@ApiOperation(notes = "退出", value = "退出")
	@RequestMapping(value = "/syslogout", method = RequestMethod.POST)
	public ModelResult<Object> logout(){
		Auth.logOut();
		return Result.build("退出成功", ApiResult.SUCCESS, ResultCode.CODE_200);
	}
}
