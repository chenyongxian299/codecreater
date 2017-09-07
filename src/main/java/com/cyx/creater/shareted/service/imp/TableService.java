package com.cyx.creater.shareted.service.imp;


import com.cyx.creater.shareted.bean.TableInfo;
import com.cyx.creater.shareted.dao.TablesDao;
import com.cyx.creater.shareted.service.ITableDescribe;
import com.cyx.creater.shareted.service.imp.base.BaseService;
import org.beetl.sql.core.*;

import java.util.List;

public class TableService implements ITableDescribe {
    private BaseService<TablesDao> baseService;
    private TablesDao tablesDao;

    public TableService(SQLManager sqlManager) {
       baseService = new BaseService<TablesDao>(sqlManager, TablesDao.class);
       this.tablesDao = baseService.getDao();
    }

    @Override
    public List<TableInfo> getTablesDescribe(String schemaName) {
        return tablesDao.queryAllTablesBySchemata(schemaName);
    }

    @Override
    public TableInfo getTableDescribe(String schemaName) {
        return null;
    }

    @Override
    public List<String> getTablesName(String schemaName) {
        return null;
    }

    @Override
    public String getTablesNameStr(String schemaName) {
        return null;
    }

    /*@Override
    public List<TableInfo> getTableDescribe(String tableName) {
        List<TableInfo> tableInfos = new ArrayList<>();
        return tableInfos;
    }

    @Override
    public String getTableFields(String tableName) {
        StringBuffer fields = new StringBuffer("");

        return fields.toString().replaceAll(", $", "");
    }*/
}
