package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysMenu;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysMenuMapper extends Mapper<SysMenu>, ConditionMapper<SysMenu>, MySqlMapper<SysMenu> {
}