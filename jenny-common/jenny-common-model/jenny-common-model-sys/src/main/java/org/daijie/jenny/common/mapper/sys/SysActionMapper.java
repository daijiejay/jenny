package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysAction;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysActionMapper extends Mapper<SysAction>, ConditionMapper<SysAction>, MySqlMapper<SysAction> {
}