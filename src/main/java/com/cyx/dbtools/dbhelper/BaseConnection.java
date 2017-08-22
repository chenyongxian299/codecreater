package com.cyx.dbtools.dbhelper;

import com.cyx.dbtools.bean.ConnectionParameter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseConnection implements IConnection {
    @Override
    public boolean isConnected(ConnectionParameter parameter) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(parameter.getUrl(), parameter.getUserName(), parameter.getPassword());
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
