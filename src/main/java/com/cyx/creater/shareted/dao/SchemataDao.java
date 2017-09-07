package com.cyx.creater.shareted.dao;

import com.cyx.creater.shareted.bean.SchemataInfo;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface SchemataDao extends BaseMapper<SchemataInfo> {
    List<SchemataInfo> queryAllSchemata();
}
