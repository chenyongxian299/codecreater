/*
 * Copyright 2014 ptma@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyx.creater.shareted.view.component;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditableTable extends JTable {

    private static final long serialVersionUID = -9082130706292915465L;
    int columnIndex = -1;
    transient TableCellEditor editor;                                // 边框编辑器

    public void setComboCell(int columnIndex, TableCellEditor editor) {
        this.columnIndex = columnIndex;
        this.editor = editor;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int columnIndex) {
        if (columnIndex == columnIndex && editor != null) {
            return editor;
        }
        return super.getCellEditor(row, columnIndex);
    }

    /**
     * 返回数据类型
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

}
