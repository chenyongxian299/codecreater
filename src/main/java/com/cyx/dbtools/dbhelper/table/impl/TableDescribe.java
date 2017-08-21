package com.cyx.dbtools.dbhelper.table.impl;


import com.cyx.dbtools.bean.TableField;
import com.cyx.dbtools.dbhelper.IConnection;
import com.cyx.dbtools.dbhelper.table.ITableDescribe;
import com.cyx.dbtools.mysql.DMLUtils;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.xml.jaxb.Table;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TableDescribe implements ITableDescribe {

    private Connection conn;

    public TableDescribe(IConnection iConn) {
        if (iConn != null) {
            this.conn = iConn.getConnection();
        } else {
            throw new RuntimeException("必须初始化一个数据库连接对象");
        }
    }

    @Override
    public List<TableField> getTableDescribe(String tableName) {
        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

        Result<Record> result = create.fetch(DMLUtils.getTableStructure(tableName));
        List<TableField> tableFields = new ArrayList<>();
        for (Record record : result) {
            TableField tableField = new TableField();
            tableField.setFieldName((String) record.get("Field"));
            tableField.setFieldType((String) record.get("Type"));
            tableField.setDefaultValue((String) record.get("Default"));
            tableField.setExtra((String) record.get("Extra"));
            tableField.setEnabledNull("YES".equals(record.get("Null")));
            tableField.setPrimaryKey("PRI".equals(record.get("Key") == null ? "" : record.get("Key")));
            tableFields.add(tableField);
        }
        return tableFields;
    }

    @Override
    public String getTableFields(String tableName) {
        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        Result<Record> result = create.fetch("describe " +tableName);
        StringBuffer fields = new StringBuffer("");
        for (Record record : result) {
            fields.append(record.get("Field")).append(", ");
        }
        return fields.toString().replaceAll(", $", "");
    }
}
