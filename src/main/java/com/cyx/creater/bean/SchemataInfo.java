package com.cyx.creater.bean;

import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 对应数据库information_schema的SCHEMATA表
 */
@Table(name="SCHEMATA")
public class SchemataInfo implements Serializable{
    private String catalogName;

    private String schemaName;

    private String defaultCharacterSetName;

    private String defaultCollationName;

    private String sqlPath;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getDefaultCharacterSetName() {
        return defaultCharacterSetName;
    }

    public void setDefaultCharacterSetName(String defaultCharacterSetName) {
        this.defaultCharacterSetName = defaultCharacterSetName;
    }

    public String getDefaultCollationName() {
        return defaultCollationName;
    }

    public void setDefaultCollationName(String defaultCollationName) {
        this.defaultCollationName = defaultCollationName;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    @Override
    public String toString() {
        return schemaName;
    }
}
