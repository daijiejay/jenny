package org.daijie.jenny.common.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(description="系统表格管理")
@FeignClient(value="${feign.sys}")
@RequestMapping(value = "/sysfeign/systable")
public interface SysTableFeign {

}
