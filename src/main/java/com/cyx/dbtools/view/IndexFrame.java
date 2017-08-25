package com.cyx.dbtools.view;

import com.cyx.dbtools.bean.ColumnInfo;
import com.cyx.dbtools.bean.ConnectionParameter;
import com.cyx.dbtools.bean.SchemataInfo;
import com.cyx.dbtools.bean.TableInfo;
import com.cyx.dbtools.dao.ColumnsDao;
import com.cyx.dbtools.dao.SchemataDao;
import com.cyx.dbtools.dao.TablesDao;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

import javax.swing.*;
import java.util.List;

public class IndexFrame {
    private JPanel plIndex;
    private JTree tree1;

    private SQLManager sqlManager;

    public JPanel getPlIndex() {
        return plIndex;
    }

    public void setPlIndex(JPanel plIndex) {
        this.plIndex = plIndex;
    }

    public JTree getTree1() {
        return tree1;
    }

    public void setTree1(JTree tree1) {
        this.tree1 = tree1;
    }

    public void initDatabaseInfo(ConnectionParameter parameter) {
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.cj.jdbc.Driver", parameter.getUrl(), parameter.getUserName(), parameter.getPassword());
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{});
        SchemataDao schemataDao = sqlManager.getMapper(SchemataDao.class);
        TablesDao tablesDao = sqlManager.getMapper(TablesDao.class);
        ColumnsDao columnsDao = sqlManager.getMapper(ColumnsDao.class);

        List<SchemataInfo> schemataInfoList = schemataDao.queryAllSchemata();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (SchemataInfo info : schemataInfoList) {
                    if(info.getSchemaName().equals("information_schema")){
                        continue;
                    }

                    System.out.println("----schema name : " + info.getSchemaName());
                    List<TableInfo> tableInfos = tablesDao.queryAllTablesBySchemata(info.getSchemaName());
                    for (TableInfo tableInfo : tableInfos) {
                        System.out.println("      ----table name : " + tableInfo.getTableName());
                        List<ColumnInfo> columnInfos = columnsDao.queryAllColumnByTable(tableInfo.getTableName());
                        for (ColumnInfo columnInfo : columnInfos) {
                            System.out.println("            ----column name : " + columnInfo.getColumnName());
                        }
                    }
                }
            }
        }).start();
    }
}
