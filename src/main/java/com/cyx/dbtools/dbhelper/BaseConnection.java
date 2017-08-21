package com.cyx.dbtools.dbhelper;

import com.cyx.dbtools.bean.ConnectionParameter;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseConnection implements IConnection {
    @Override
    public boolean isConnected(ConnectionParameter parameter) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return !DriverManager.getConnection(parameter.getUrl(), parameter.getUserName(), parameter.getPassword()).isClosed();
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
