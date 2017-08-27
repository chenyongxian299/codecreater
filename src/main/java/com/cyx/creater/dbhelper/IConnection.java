package com.cyx.creater.dbhelper;

import com.cyx.creater.bean.ConnectionParameter;

import java.sql.Connection;

public interface IConnection{
    Connection getConnection();
    boolean isConnected(ConnectionParameter parameter);
}
