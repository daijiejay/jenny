package org.daijie.jenny.common.mapper.sys;

import org.daijie.jenny.common.model.sys.SysOperateAuthorized;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysOperateAuthorizedMapper extends Mapper<SysOperateAuthorized>, ConditionMapper<SysOperateAuthorized>, MySqlMapper<SysOperateAuthorized> {
}