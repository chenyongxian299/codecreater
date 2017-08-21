package com.cyx.dbtools.bean;

public class TableAttribute {
    public enum TableType {
        BASE_TABLE, VIEW;
    }

    /**
     * 表所属的数据库
     */
    private String schema;

    /**
     * 表名
     */
    private String name;

    /**
     * 表类型（物理表或者视图）
     */
    private TableType tableType;

    /**
     * 表备注
     */
    private String comment;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}