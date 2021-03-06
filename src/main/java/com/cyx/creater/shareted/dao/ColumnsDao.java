package com.cyx.creater.shareted.dao;

import com.cyx.creater.shareted.bean.ColumnInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface ColumnsDao extends BaseMapper<ColumnInfo> {
    List<ColumnInfo> queryAllColumnByTable(@Param("tableName") String tableName);

    String queryColumnStrByTable(@Param("tableName") String tableName);
}
