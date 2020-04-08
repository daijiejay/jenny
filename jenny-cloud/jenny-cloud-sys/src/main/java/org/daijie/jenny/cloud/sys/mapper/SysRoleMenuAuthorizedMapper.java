package org.daijie.jenny.cloud.sys.mapper;

import org.daijie.jdbc.annotation.Mapper;
import org.daijie.jdbc.annotation.Param;
import org.daijie.jdbc.annotation.Select;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;

import java.util.List;

@Mapper
public interface SysRoleMenuAuthorizedMapper {

	/**
	 * 根据用户编号查询已授权的菜单
	 * -1时查询所有的菜单
	 * @param userId 用户编号
	 * @return List
	 */
	@Select("select " +
			"sys_menu.menu_id as menuId," +
			"sys_menu.menu_name as menuName," +
			"sys_menu.level," +
			"sys_menu.parent_id as parentId," +
			"sys_menu.sort," +
			"sys_menu.mapping," +
			"sys_menu.icon " +
			"from " +
			"sys_menu " +
			"left join sys_menu_authorized on sys_menu.menu_id = sys_menu_authorized.menu_id " +
			"left join sys_role_authorized on sys_menu_authorized.role_id = sys_role_authorized.role_id " +
			"where 1=1 " +
			"<if test=\"userId != -1\"> " +
			"and sys_role_authorized.user_id = #{userId} " +
			"</if> " +
			"group by sys_menu.menu_id " +
			"order by sys_menu.menu_code")
	List<SysMenuResponse> selectRoleMenuAuthorized(@Param(value="userId") Integer userId);
}
