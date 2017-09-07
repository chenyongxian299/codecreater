package com.cyx.creater.shareted.dbhelper;

import com.cyx.creater.shareted.bean.ConnectionParameter;

import java.sql.Connection;

public interface IConnection{
    Connection getConnection();
    boolean isConnected(ConnectionParameter parameter);
}
