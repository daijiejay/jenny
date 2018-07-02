package org.daijie.jenny.common.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(description="系统功能权限管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/sysaction")
public interface SysActionFeign {

}
