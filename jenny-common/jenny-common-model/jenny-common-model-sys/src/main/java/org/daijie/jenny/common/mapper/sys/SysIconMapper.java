package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysIcon;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysIconMapper extends Mapper<SysIcon>, ConditionMapper<SysIcon>, MySqlMapper<SysIcon> {
}