package com.cyx.creater.dbhelper;

import javax.sql.DataSource;

public interface IDataSource extends IConnection {
    DataSource getDataSource();
}
