package com.cyx.creater.shareted.dao;

import com.cyx.creater.shareted.bean.TableInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface TablesDao extends BaseMapper<TableInfo>{
    List<TableInfo> queryAllTablesBySchemata(@Param("schemaName")String schemaName);

}
