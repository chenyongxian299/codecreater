package com.cyx.creater.shareted.service.imp;

import com.cyx.creater.shareted.bean.ColumnInfo;
import com.cyx.creater.shareted.dao.ColumnsDao;
import com.cyx.creater.shareted.service.IColumnDescribe;
import com.cyx.creater.shareted.service.imp.base.BaseService;
import org.beetl.sql.core.SQLManager;

import java.util.List;

public class ColumnService implements IColumnDescribe {

    private BaseService<ColumnsDao> baseService;
    private ColumnsDao columnsDao;

    public ColumnService(SQLManager sqlManager) {
        baseService = new BaseService<ColumnsDao>(sqlManager, ColumnsDao.class);
        columnsDao = baseService.getDao();
    }

    @Override
    public List<ColumnInfo> getColumnsDescribe(String tableName) {
        return columnsDao.queryAllColumnByTable(tableName);
    }

    @Override
    public ColumnInfo getColumnDescribe(String tableName) {
        return null;
    }

    @Override
    public List<String> getColumnsName(String tableName) {
        return null;
    }

    @Override
    public String getColumnsNameStr(String tableName) {
        return columnsDao.queryColumnStrByTable(tableName);
    }
}
