package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysUser;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysUserMapper extends Mapper<SysUser>, ConditionMapper<SysUser>, MySqlMapper<SysUser> {
}