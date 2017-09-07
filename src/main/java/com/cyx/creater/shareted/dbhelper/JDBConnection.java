package com.cyx.creater.shareted.dbhelper;

import com.cyx.creater.shareted.bean.ConnectionParameter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection extends BaseConnection {
    private Connection connection;

    private static JDBConnection jdbConnection;


    private JDBConnection(ConnectionParameter parameter) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(parameter.getUrl(), parameter.getUserName(), parameter.getPassword());
        } catch (ClassNotFoundException e) {
            jdbConnection = null;
        } catch (SQLException e) {
            jdbConnection = null;
        }
    }

    public synchronized static IConnection getInstance(ConnectionParameter parameter) {
        if (jdbConnection == null) {
            // synchronized (jdbConnection) {
            jdbConnection = new JDBConnection(parameter);
            // }
        }
        return jdbConnection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
