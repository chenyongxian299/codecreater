package com.cyx.creater.dbhelper;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BeetlSql {

    Map<String, SQLManager> sqlManagerMap = new HashMap<>();

    private static final BeetlSql BEETL_SQL = new BeetlSql();

    private BeetlSql() {

    }

    public static BeetlSql getInstance() {
        return BeetlSql.BEETL_SQL;
    }

    /**
     * 注册一个sqlManager
     *
     * @param mdPath     .md文件的相对路径（相对于resource的文件目录)
     * @param dataSource IDataSource 数据源的实现类
     */
    public SQLManager registerSqlManager(String mdPath, IDataSource dataSource) {
        if (dataSource == null) {
            throw new RuntimeException("请提供一个不为空的数据源实现类");
        }
        SQLManager sqlManager = sqlManagerMap.get(mdPath);
        if (null == sqlManager) {
            ConnectionSource source = ConnectionSourceHelper.getSingle(dataSource.getDataSource());
            DBStyle mysql = new MySqlStyle();
            // sql语句放在classpagth的/sql 目录下
            SQLLoader loader = new ClasspathLoader(mdPath);
            // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
            UnderlinedNameConversion nc = new UnderlinedNameConversion();
            // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
            sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{});
            sqlManagerMap.put(mdPath, sqlManager);
        }
        return sqlManager;
    }

    /**
     * 移除一个sqlManager
     *
     * @param mdPath .md文件的相对路径（相对于resource的文件目录)
     */
    public void destroySqlManager(String mdPath) {
        SQLManager sqlManager = sqlManagerMap.get(mdPath);
        if (null != sqlManager) {
            try {
                sqlManager.getDs().getMaster().close();
                sqlManagerMap.remove(mdPath);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取sqlManager
     *
     * @param mdPath .md文件的相对路径（相对于resource的文件目录)
     * @return SQLManager
     */
    public SQLManager getSqlManager(String mdPath) {
        return sqlManagerMap.get(mdPath);
    }
}
