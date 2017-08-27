package com.cyx.creater.view;

import com.cyx.creater.bean.ColumnInfo;
import com.cyx.creater.bean.SchemataInfo;
import com.cyx.creater.bean.TableInfo;
import com.cyx.creater.dbhelper.BeetlSql;
import com.cyx.creater.dbhelper.IDataSource;
import com.cyx.creater.service.IColumnDescribe;
import com.cyx.creater.service.ISchemaDescribe;
import com.cyx.creater.service.imp.ColumnService;
import com.cyx.creater.service.imp.SchemaService;
import com.cyx.creater.service.ITableDescribe;
import com.cyx.creater.service.imp.TableService;
import org.beetl.sql.core.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class IndexFrame {
    private JPanel plIndex;
    private JTree tree1;

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

    public void initDatabaseInfo(IDataSource dataSource) {

        BeetlSql beetlSql = BeetlSql.getInstance();
        SQLManager sqlManager = beetlSql.registerSqlManager("/sql/mysql", dataSource);
        ISchemaDescribe schemaService = new SchemaService(sqlManager);
        ITableDescribe tableService = new TableService(sqlManager);
        IColumnDescribe columnService = new ColumnService(sqlManager);
        List<SchemataInfo> schemataInfoList = schemaService.getSchemataDescribe();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库");
                for (SchemataInfo info : schemataInfoList) {
                    DefaultMutableTreeNode schemataNode = new DefaultMutableTreeNode(info.getSchemaName());
                    root.add(schemataNode);
                    if (info.getSchemaName().equals("information_schema")) {
                        continue;
                    }
                    List<TableInfo> tableInfos = tableService.getTablesDescribe(info.getSchemaName());
                    for (TableInfo tableInfo : tableInfos) {
                        DefaultMutableTreeNode tablesNode = new DefaultMutableTreeNode(tableInfo.getTableName());
                        schemataNode.add(tablesNode);
                        List<ColumnInfo> columnInfos = columnService.getColumnsDescribe(tableInfo.getTableName());
                        for (ColumnInfo columnInfo : columnInfos) {
                            DefaultMutableTreeNode columnsNode = new DefaultMutableTreeNode(columnInfo.getColumnName() + "   " +columnInfo.getColumnType());
                            tablesNode.add(columnsNode);
                        }
                    }
                }
                tree1.setModel(new DefaultTreeModel(root));
            }
        }).start();
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

}