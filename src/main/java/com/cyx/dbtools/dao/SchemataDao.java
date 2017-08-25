package com.cyx.dbtools.dao;

import com.cyx.dbtools.bean.SchemataInfo;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface SchemataDao extends BaseMapper<SchemataInfo> {
    List<SchemataInfo> queryAllSchemata();

}
