package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysRole;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysRoleMapper extends Mapper<SysRole>, ConditionMapper<SysRole>, MySqlMapper<SysRole> {
}