package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysUserAuthorized;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysUserAuthorizedMapper extends Mapper<SysUserAuthorized>, ConditionMapper<SysUserAuthorized>, MySqlMapper<SysUserAuthorized> {
}