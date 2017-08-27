package com.cyx.creater.dao;

import com.cyx.creater.bean.SchemataInfo;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface SchemataDao extends BaseMapper<SchemataInfo> {
    List<SchemataInfo> queryAllSchemata();
}
