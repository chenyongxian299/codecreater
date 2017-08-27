package com.cyx.creater.service.imp.base;

import org.beetl.sql.core.SQLManager;

public class BaseService<T> {
    private T subDao;
    public BaseService(SQLManager sqlManager, Class<T> dao){
           subDao = sqlManager.getMapper(dao);
    }
    public T getDao(){
        return subDao;
    }
}
