package com.cyx.creater.dbhelper;

import com.cyx.creater.bean.ConnectionParameter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseConnection implements IDataSource {
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

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
