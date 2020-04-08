package org.daijie.jenny.api.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.daijie.jenny.common.feign.sys.SysRoleAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.SysServerFeign;
import org.daijie.jenny.common.feign.sys.SysUserFeign;
import org.daijie.jenny.common.feign.sys.request.SysUserLoginRequest;
import org.daijie.jenny.common.feign.sys.response.SysServerResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserCacheResponse;
import org.daijie.jenny.common.feign.sys.response.SysUserPasswordResponse;
import org.daijie.shiro.authc.Auth;
import org.daijie.shiro.session.ShiroRedisSession.Redis;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="登录管理")
@RestController
public class SysLoginController {

	@Autowired
	private SysUserFeign sysUserFeign;

	@Autowired
	private SysRoleAuthorizedFeign sysRoleAuthorizedFeign;

	@Autowired
	private SysServerFeign sysServerFeign;

	@ApiOperation(value = "登录")
	@RequestMapping(value = "/syslogin", method = RequestMethod.POST)
	public ModelResult<Boolean> login(@RequestBody SysUserLoginRequest sysUserLoginRequest) {
		SysUserCacheResponse sysUserResponse = sysUserFeign.getUserByUsername(sysUserLoginRequest.getUsername()).getData();
		if (sysUserResponse != null) {
			if (sysUserResponse.getEnable()) {
				return Result.build("用户已被禁用！", Result.ERROR, ResultCode.CODE_100);
			}
			if (sysUserResponse.getAdmin()) {
				Auth.setRoles(new String[]{"ADMIN"});
			} else {
				Auth.setRoles(sysRoleAuthorizedFeign.getRolesByUser(sysUserResponse.getUserId()).getData());
			}
			SysUserPasswordResponse passwordResponse = sysUserFeign.getUserPasswordById(sysUserResponse.getUserId()).getData();
			Auth.login(sysUserLoginRequest.getUsername(), sysUserLoginRequest.getPassword(), passwordResponse.getSalt(), passwordResponse.getPassword(), sysUserResponse);
			return Result.build(true, "登录成功", Result.SUCCESS);
		}
		return Result.build("用户名或密码错误！", Result.ERROR, ResultCode.CODE_100);
	}

	@ApiOperation(value = "退出")
	@RequestMapping(value = "/syslogout", method = RequestMethod.POST)
	public ModelResult<Object> logout(){
		Auth.logOut();
		return Result.build("退出成功", Result.SUCCESS, ResultCode.CODE_200);
	}

	@ApiOperation(value = "获取加密公钥")
	@RequestMapping(value = "/publicKey", method = RequestMethod.GET)
	public ModelResult<String> getPublicKey() {
		String publicKey = Auth.getPublicKey();
		return Result.build(publicKey);
	}

	@ApiOperation(value = "根据服务名称获取服务信息")
	@RequestMapping(value = "/server/{serverId}", method = RequestMethod.GET)
	public ModelResult<SysServerResponse> getServer(@PathVariable("serverId") String serverId) {
		@SuppressWarnings("unchecked")
		List<SysServerResponse> servers = (List<SysServerResponse>) Redis.get("servers");
		if (servers == null || servers.isEmpty()) {
			servers = sysServerFeign.getServerAll().getData();
			Redis.set("servers", servers);
		}
		return Result.build(servers.stream().filter(server -> server.getServerId().equals(serverId)).findFirst().orElse(null));
	}
}
