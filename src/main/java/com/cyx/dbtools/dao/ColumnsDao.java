package com.cyx.dbtools.dao;

import com.cyx.dbtools.bean.ColumnInfo;
import com.cyx.dbtools.bean.TableInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("info")
public interface ColumnsDao extends BaseMapper<ColumnInfo> {
    List<ColumnInfo> queryAllColumnByTable(@Param("tableName") String tableName);
}
