package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysServer;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysServerMapper extends Mapper<SysServer>, ConditionMapper<SysServer>, MySqlMapper<SysServer> {
}