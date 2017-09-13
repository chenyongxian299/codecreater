package com.cyx.creater.shareted.view.model;

import com.alibaba.fastjson.JSON;
import com.cyx.creater.shareted.bean.ColumnInfo;
import com.cyx.creater.shareted.view.model.bean.TableColumn;
import com.cyx.creater.utils.Console;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;

public class DBTableModel implements TableModel {

    private List<TableColumn> tableColumns;
    private TableTitle[] tableTitles = new TableTitle[]{
            new TableTitle("列名", "columnName"),
            new TableTitle("主键", "isPrimaryKey"),
            new TableTitle("显示", "isShow"),
            new TableTitle("搜索", "isSearch"),
            new TableTitle("排序", "isOrder")
    };

    public DBTableModel(List<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }

    @Override
    public int getRowCount() {
        return tableColumns.size();
    }

    @Override
    public int getColumnCount() {
        return tableTitles.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnIndex < tableTitles.length ? tableTitles[columnIndex].getFieldText() : "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex > 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        if (tableColumns != null && tableColumns.size() > rowIndex) {
            TableColumn tableColumn = tableColumns.get(rowIndex);
            String columnName = columnIndex < tableTitles.length ? tableTitles[columnIndex].getFieldValue() : "";
            if (!"".equals(columnName)) {
                try {
                    Field field = tableColumn.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    result = field.get(tableColumn);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (tableColumns != null && tableColumns.size() > rowIndex) {
            TableColumn tableColumn = tableColumns.get(rowIndex);
            String columnName = columnIndex < tableTitles.length ? tableTitles[columnIndex].getFieldValue() : "";
            if (!"".equals(columnName)) {
                try {
                    Field field = tableColumn.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(tableColumn, aValue);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    private class TableTitle {
        private String FieldValue;
        private String FieldText;

        public TableTitle() {
        }

        public TableTitle(String text, String value) {
            this.FieldText = text;
            this.FieldValue = value;
        }

        public String getFieldValue() {
            return FieldValue;
        }

        public void setFieldValue(String fieldValue) {
            FieldValue = fieldValue;
        }

        public String getFieldText() {
            return FieldText;
        }

        public void setFieldText(String fieldText) {
            FieldText = fieldText;
        }
    }
}
