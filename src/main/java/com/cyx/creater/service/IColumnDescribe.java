package com.cyx.creater.service;

import com.cyx.creater.bean.ColumnInfo;

import java.util.List;

public interface IColumnDescribe {

    List<ColumnInfo> getColumnsDescribe(String tableName);

    ColumnInfo getColumnDescribe(String tableName);

    List<String> getColumnsName(String tableName);

    String getColumnsNameStr(String tableName);
}
