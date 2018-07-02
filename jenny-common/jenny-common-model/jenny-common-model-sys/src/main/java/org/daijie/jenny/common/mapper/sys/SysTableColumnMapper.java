package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysTableColumn;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysTableColumnMapper extends Mapper<SysTableColumn>, ConditionMapper<SysTableColumn>, MySqlMapper<SysTableColumn> {
}