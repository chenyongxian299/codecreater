package com.cyx.creater.shareted.bean;

import org.beetl.sql.core.annotatoin.Table;

import java.util.ArrayList;
import java.util.List;

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

    private List<ColumnInfo> columnInfos = new ArrayList<>();

    public List<ColumnInfo> getColumnInfos() {
        return columnInfos;
    }

    public void setColumnInfos(List<ColumnInfo> columnInfos) {
        this.columnInfos = columnInfos;
    }

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
