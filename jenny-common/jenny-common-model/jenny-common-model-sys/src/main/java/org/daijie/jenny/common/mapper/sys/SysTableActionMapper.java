package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysTableAction;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysTableActionMapper extends Mapper<SysTableAction>, ConditionMapper<SysTableAction>, MySqlMapper<SysTableAction> {
}