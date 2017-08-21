package com.cyx.dbtools;

import com.alibaba.fastjson.JSON;
import com.cyx.dbtools.bean.TableField;
import com.cyx.dbtools.dbhelper.C3P0Connection;
import com.cyx.dbtools.dbhelper.IConnection;
import com.cyx.dbtools.dbhelper.table.impl.TableDescribe;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.util.derby.sys.Sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public void execute() {
        IConnection iConnection = C3P0Connection.getInstance();
        TableDescribe tableDescribe = new TableDescribe(iConnection);
        List<TableField> tableFields = tableDescribe.getTableDescribe("user_info");
        for (TableField tableField : tableFields) {
            System.err.println("table test fields : " + JSON.toJSONString(tableFields));
        }
    }

    public static void main(String[] arg) {

    }
}
