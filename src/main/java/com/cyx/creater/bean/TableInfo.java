package com.cyx.creater.bean;

import org.beetl.sql.core.annotatoin.Table;

@Table(name="TABLES")
public class TableInfo {
    public enum TableType {
        BASE_TABLE, VIEW;
    }

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private String tableCollation;


    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
