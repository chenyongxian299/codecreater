package com.cyx.dbtools.mysql;

public class DMLUtils {

    private final static String TABLE_INFO = "SELECT table_schema schema, table_name name, table_type type,table_comment `comment` FROM information_schema.tables WHERE table_schema='%s'";

    private final static String TABLE_STRUCTURE = "DESCRIBE %s";

    /**
     * 获取数据库中表与视图的信息
     */
    public static String getTableInfoSql(String databaseName) {
        return String.format(DMLUtils.TABLE_INFO, databaseName);
    }
    /**
     * 获取表结构
     */
    public static String getTableStructure(String tableName) {
        return String.format(DMLUtils.TABLE_STRUCTURE, tableName);
    }

}
