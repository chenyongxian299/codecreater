package com.cyx.creater.service.imp;

import com.cyx.creater.bean.SchemataInfo;
import com.cyx.creater.dao.SchemataDao;
import com.cyx.creater.service.ISchemaDescribe;
import com.cyx.creater.service.imp.base.BaseService;
import org.beetl.sql.core.SQLManager;

import java.util.List;

public class SchemaService implements ISchemaDescribe {
    private SchemataDao schemataDao;
    private BaseService<SchemataDao> baseService;

    public SchemaService(SQLManager sqlManager) {
        baseService = new BaseService<SchemataDao>(sqlManager, SchemataDao.class);
        schemataDao = baseService.getDao();
    }

    @Override
    public List<SchemataInfo> getSchemataDescribe() {
        return schemataDao.queryAllSchemata();
    }

    @Override
    public SchemataInfo getSchemaDescribe() {
        return null;
    }

    @Override
    public List<String> getSchemataName() {
        return null;
    }

    @Override
    public String getSchemaName() {
        return null;
    }
}
