package com.cyx.creater.service;


import com.cyx.creater.bean.TableInfo;

import java.util.List;

public interface ITableDescribe {

    List<TableInfo> getTablesDescribe(String schemaName);

    TableInfo getTableDescribe(String schemaName);

    List<String> getTablesName(String schemaName);

    String getTablesNameStr(String schemaName);
}
