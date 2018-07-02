package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysTable;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysTableMapper extends Mapper<SysTable>, ConditionMapper<SysTable>, MySqlMapper<SysTable> {
}