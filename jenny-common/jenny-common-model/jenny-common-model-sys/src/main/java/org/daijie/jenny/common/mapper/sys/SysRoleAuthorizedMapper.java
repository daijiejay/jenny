package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysRoleAuthorized;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysRoleAuthorizedMapper extends Mapper<SysRoleAuthorized>, ConditionMapper<SysRoleAuthorized>, MySqlMapper<SysRoleAuthorized> {
}