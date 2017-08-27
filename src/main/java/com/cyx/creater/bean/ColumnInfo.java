package com.cyx.creater.bean;

import org.beetl.sql.core.annotatoin.Table;

@Table(name="COLUMNS")
public class ColumnInfo {

    private String columnName;

    private long ordinalPosition;

    private String columnDefault;

    private String isNullAble;

    private String dataType;

    private long characterMaximumLength;

    private long characterOctetLength;

    private long numericPrecision;

    private long numericScale;

    private long datetimePrecision;

    private String characterSetName;

    private String collationName;

    private String columnType;

    private String columnKey;

    private String extra;

    private String privileges;

    private String columnComment;

    private String generationExpression;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullAble() {
        return isNullAble;
    }

    public void setIsNullAble(String isNullAble) {
        this.isNullAble = isNullAble;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(long numericScale) {
        this.numericScale = numericScale;
    }

    public long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }
}
