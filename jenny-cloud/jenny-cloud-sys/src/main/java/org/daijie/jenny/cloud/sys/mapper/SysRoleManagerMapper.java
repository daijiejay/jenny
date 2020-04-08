package org.daijie.jenny.cloud.sys.mapper;

import org.daijie.jdbc.annotation.Mapper;
import org.daijie.jdbc.annotation.Select;
import org.daijie.jenny.common.feign.sys.response.SysMenuSelectedResponse;

import java.util.List;

@Mapper
public interface SysRoleManagerMapper {
	
	/**
	 * 根据用户编号查询已授权的角色代码数组
	 * @param userId 用户编号
	 * @return List 角色代码数组
	 */
	@Select("select" +
			"sys_role.role_code" +
			"from" +
			"sys_role_authorized," +
			"sys_role" +
			"where 1=1" +
			"and sys_role_authorized.role_id = sys_role.role_id" +
			"and sys_role_authorized.user_id = #{userId}")
	public List<String> selectRolesByUser(Integer userId);
	
	/**
	 * 查询所有菜单和操作功能，以树结构返回数组
	 * @param roleId 角色编号
	 * @return List 树结构数组
	 */
	@Select("select " +
			"sys_menu.menu_id as id," +
			"sys_menu.parent_id as pid," +
			"sys_menu.menu_name as name," +
			"if(sys_menu_authorized.menu_authorized_id is null, 0, 1) as checked," +
			"sys_menu.menu_code as ordername" +
			"from " +
			"sys_menu" +
			"left join sys_menu_authorized on (sys_menu.menu_id = sys_menu_authorized.menu_id and sys_menu_authorized.role_id = #{roleId})" +
			"union " +
			"select " +
			"sys_table_action.action_id*-1 as id," +
			"sys_table.menu_id as pid," +
			"concat(sys_table.table_name,\"-\",sys_table_action.action_name) as name," +
			"if(sys_operate_authorized.operate_authorized_id is null, 0, 1) as checked," +
			"'999999' as ordername" +
			"from " +
			"sys_table_action" +
			"left join sys_table on sys_table_action.table_id = sys_table.table_id" +
			"left join sys_operate_authorized on (sys_table_action.action_id = sys_operate_authorized.operate_id and sys_operate_authorized.role_id = #{roleId})" +
			"order by ordername,pid")
	public List<SysMenuSelectedResponse> selectSelectedMenuAndOpreate(Integer roleId);
}