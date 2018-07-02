package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysMenuAuthorized;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysMenuAuthorizedMapper extends Mapper<SysMenuAuthorized>, ConditionMapper<SysMenuAuthorized>, MySqlMapper<SysMenuAuthorized> {
}