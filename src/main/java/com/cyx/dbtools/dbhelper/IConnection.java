package com.cyx.dbtools.dbhelper;

import com.cyx.dbtools.bean.ConnectionParameter;

import java.sql.Connection;

public interface IConnection {
    Connection getConnection();
    boolean isConnected(ConnectionParameter parameter);
}
