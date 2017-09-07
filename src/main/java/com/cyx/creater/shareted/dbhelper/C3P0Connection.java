package com.cyx.creater.shareted.dbhelper;


import com.cyx.creater.shareted.bean.ConnectionParameter;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class C3P0Connection extends BaseConnection {
    private static ComboPooledDataSource connPool;

    public final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    //静态初始化块进行初始化
    //网站模式下才使用
   /* static {
        try {
            Properties prop = new Properties();
            InputStream in = C3P0Connection.class.getResourceAsStream("/config/db_config.properties");
            try {
                prop.load(in);
                connPool = new ComboPooledDataSource();//创建连接池实例
                connPool.setDriverClass(C3P0Connection.DRIVER);//设置连接池连接数据库所需的驱动
                connPool.setJdbcUrl(prop.getProperty("c3p0.jdbcUrl").trim());//设置连接数据库的URL
                connPool.setUser(prop.getProperty("c3p0.username").trim());//设置连接数据库的用户名
                connPool.setPassword(prop.getProperty("c3p0.password").trim());//设置连接数据库的密码
                connPool.setMaxPoolSize(StringUtil.toInteger(prop.getProperty("c3p0.maxPoolSize")));//设置连接池的最大连接数
                connPool.setMinPoolSize(StringUtil.toInteger(prop.getProperty("c3p0.minPoolSize")));//设置连接池的最小连接数
                connPool.setInitialPoolSize(StringUtil.toInteger(prop.getProperty("c3p0.initPoolSize")));//设置连接池的初始连接数
                connPool.setMaxStatements(StringUtil.toInteger(prop.getProperty("c3p0.maxStatements")));//设置连接池的缓存Statement的最大数
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private C3P0Connection(ConnectionParameter parameter) {
        try {
            connPool = new ComboPooledDataSource();//创建连接池实例
            connPool.setDriverClass(C3P0Connection.DRIVER);//设置连接池连接数据库所需的驱动
            connPool.setJdbcUrl(parameter.getUrl());//设置连接数据库的URL
            connPool.setUser(parameter.getUserName());//设置连接数据库的用户名
            connPool.setPassword(parameter.getPassword());//设置连接数据库的密码
            connPool.setMaxPoolSize(parameter.getMaxPoolSize());//设置连接池的最大连接数
            connPool.setMinPoolSize(parameter.getMinPoolSize());//设置连接池的最小连接数
            connPool.setInitialPoolSize(parameter.getInitPoolSize());//设置连接池的初始连接数
            connPool.setMaxStatements(parameter.getMaxStatements());//设置连接池的缓存Statement的最大数
        } catch (PropertyVetoException e) {
            c3P0Connection = null;
            e.printStackTrace();
        }
    }

    private static C3P0Connection c3P0Connection;

    public static synchronized C3P0Connection getInstance(ConnectionParameter parameter) {
        if (c3P0Connection == null) {
            synchronized (C3P0Connection.class) {
                c3P0Connection = new C3P0Connection(parameter);
            }
        }
        return c3P0Connection;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connPool.getConnection();
        } catch (SQLException e) {
            try {
                Class.forName(C3P0Connection.DRIVER);
                connection = DriverManager.getConnection(connPool.getJdbcUrl(), connPool.getUser(), connPool.getPassword());
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public DataSource getDataSource() {
        return connPool;
    }
}
