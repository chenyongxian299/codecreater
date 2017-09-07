package com.cyx.creater.shareted.dbhelper;

import javax.sql.DataSource;

public interface IDataSource extends IConnection {
    DataSource getDataSource();
}
