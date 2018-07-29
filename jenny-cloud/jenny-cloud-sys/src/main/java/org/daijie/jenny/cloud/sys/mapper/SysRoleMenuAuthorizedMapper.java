package org.daijie.jenny.cloud.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;

public interface SysRoleMenuAuthorizedMapper {

	/**
	 * 根据用户编号查询已授权的菜单
	 * -1时查询所有的菜单
	 * @param userId 用户编号
	 * @return List
	 */
	List<SysMenuResponse> selectRoleMenuAuthorized(@Param(value="userId") Integer userId);
}
