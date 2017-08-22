package com.cyx.dbtools.mysql;

public class DMLUtils {

    private final static String TABLE_STRUCTURE = "DESCRIBE %s";

    private final static String SCHEMA_TABLE =
            "    SELECT info.TABLE_NAME table_name" +
            "         , info.TABLE_TYPE table_type" +
            "         , info.TABLE_COMMENT table_comment" +
            "    FROM information_schema.`TABLES` info " +
            "    WHERE info.`TABLE_SCHEMA` = ? ";

    /**
     * 获取数据库中表与视图的信息
     */
    public static String getSchemaTableSql(String databaseName) {
        return String.format(DMLUtils.SCHEMA_TABLE, databaseName);
    }
    /**
     * 获取表结构
     */
    public static String getTableStructure(String tableName) {
        return String.format(DMLUtils.TABLE_STRUCTURE, tableName);
    }

}
