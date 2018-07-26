package org.daijie.jenny.cloud.sys.mapper;

import java.util.List;

import org.daijie.jenny.common.feign.sys.response.SysMenuSelectedResponse;

public interface SysRoleManagerMapper {
	
	/**
	 * 根据用户编号查询已授权的角色代码数组
	 * @param userId 用户编号
	 * @return List 角色代码数组
	 */
	public List<String> selectRolesByUser(Integer userId);
	
	/**
	 * 查询所有菜单和操作功能，以树结构返回数组
	 * @param roleId 角色编号
	 * @return List 树结构数组
	 */
	public List<SysMenuSelectedResponse> selectSelectedMenuAndOpreate(Integer roleId);
}