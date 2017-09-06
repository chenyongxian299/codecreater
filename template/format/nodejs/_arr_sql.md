<%
print('_arr_sql.push(\'    SELECT');
for(column in columnList){
        if(columnLP.index == 1){//下标从1开始
           print(' ' + column.columnName + '\');\n');
        }else{
           print('_arr_sql.push(\'         , ' + column.columnName + '\');\n');
        }
}
%>