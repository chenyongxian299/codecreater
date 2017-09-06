var ${pageFileDir}_list_datagrid_is_click_command = false;

$(function () {
    shareted_other_hubspot_messenger_init();

    ${pageFileDir}_list_datagrid_search_init();
    ${pageFileDir}_list_datagrid_init();

    shareted_common_dialog_init();
});

function ${pageFileDir}_list_datagrid_search_init() {
   /* $('#${pageFileDir}_list_ipt_search_user_name').textbox({
        value: ''
    });
    $('#${pageFileDir}_list_ipt_search_name').textbox({
        value: ''
    });
    shareted_easyui_textbox_ipt_search_phone_init($('#${pageFileDir}_list_ipt_search_phone'));
    shareted_easyui_combobox_ipt_search_is_active_init($('#${pageFileDir}_list_ipt_search_is_active'));*/
}

function ${pageFileDir}_list_datagrid_search_commit() {
 /*   $('#${pageFileDir}_list_datagrid').datagrid('resize');
    $('#${pageFileDir}_list_datagrid').datagrid('clearSelections');
    $('#${pageFileDir}_list_datagrid').datagrid('load', ${pageFileDir}_list_datagrid_search_get());*/
}

function ${pageFileDir}_list_datagrid_search_reset() {
   /* $('#${pageFileDir}_list_ipt_search_user_name').textbox('setValue', '');
    $('#${pageFileDir}_list_ipt_search_name').textbox('setValue', '');
    $('#${pageFileDir}_list_ipt_search_phone').textbox('setValue', '');
    shareted_easyui_combobox_ipt_search_is_active_reset($('#${pageFileDir}_list_ipt_search_is_active'));*/
}

function ${pageFileDir}_list_datagrid_search_get() {
    return {
      /*  con_org_id_include_sub: 1,
        con_user_name: $('#${pageFileDir}_list_ipt_search_user_name').textbox('getValue'),
        con_name: $('#${pageFileDir}_list_ipt_search_name').textbox('getValue'),
        con_phone: $('#${pageFileDir}_list_ipt_search_phone').textbox('getValue'),
        con_is_active: $('#${pageFileDir}_list_ipt_search_is_active').combobox('getValue')*/
    };
}

function ${pageFileDir}_list_datagrid_init() {
    $('#${pageFileDir}_list_datagrid').datagrid({
        url: project_path_root + '${listUrl}',
        method: 'POST',
        queryParams: ${pageFileDir}_list_datagrid_search_get(),
        showHeader: true,
        striped: true,
        singleSelect: false,
        fitColumns: true,
        nowrap: false,
        rownumbers: true,
        rownumberWidth: project_datagrid_columns_row_number_width,
        showFooter: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 50],
        frozenColumns: [[]],
        columns: [[shareted_easyui_datagrid_columns_icheck('${pageFileDir}_list_datagrid'), {
            field: 'command',
            title: '操作',
            width: project_datagrid_columns_command_width_6,
            halign: project_datagrid_columns_command_halign,
            align: project_datagrid_columns_command_align,
            formatter: function (value, row, index) {
                var html = '';
                html += '&nbsp&nbsp';
                html += '<button onclick="${pageFileDir}_list_datagrid_is_click_command = true; ${pageFileDir}_set_dg_show({}, ' + row.id + ')" class="btn btn-s btn-primary">编辑</button>';
                html += '&nbsp&nbsp';
                html += '<button onclick="${pageFileDir}_list_datagrid_is_click_command = true; ${pageFileDir}_list_datagrid_del(' + row.id + ')" class="btn btn-s btn-primary">删除</button>';
                html += '&nbsp&nbsp';
                return html;
            }
        }, {
            field: 'user_name',
            title: '用户名',
            width: '100px',
            fill: true,
            halign: project_datagrid_columns_name_halign,
            align: project_datagrid_columns_name_align
        }, shareted_easyui_datagrid_columns_is_active()]],
        onBeforeLoad: function (param) {
        },
        loadFilter: function (data) {
            return data;
        },
        onLoadSuccess: function (data) {
            if (data.total < 0) {
                shareted_other_hubspot_messenger_show('获取数据失败！' + data.result.msg);
            } else {
                shareted_easyui_datagrid_columns_icheck_init('${pageFileDir}_list_datagrid');
                shareted_easyui_datagrid_columns_command_init();
                $('.lightcase_image').lightcase({
                    transition: 'elastic'
                });
                $('.stretch').anystretch();
            }
        },
        onLoadError: function () {
            shareted_other_hubspot_messenger_show('获取数据失败！');
        },
        onClickRow: function (index, row) {
        },
        onDblClickRow: function (index, row) {
            $('#${pageFileDir}_list_datagrid').datagrid('clearSelections');
            $('#${pageFileDir}_list_datagrid').datagrid('selectRow', index);
            if ('{{admin_user.right.11}}' == 'display') {
                ${pageFileDir}
                _set_dg_show({}, row.id);
            }
        },
        onClickCell: function (index, field, value) {
        },
        onDblClickCell: function (index, field, value) {
        },
        onSelect: function (index, row) {
            if (${pageFileDir}_list_datagrid_is_click_command) {
                ${pageFileDir}
                _list_datagrid_is_click_command = false;
                $('#${pageFileDir}_list_datagrid').datagrid('unselectRow', index);
            } else {
                shareted_easyui_datagrid_columns_icheck_select('${pageFileDir}_list_datagrid', index);
            }
        },
        onUnselect: function (index, row) {
            if (${pageFileDir}_list_datagrid_is_click_command) {
                ${pageFileDir}_list_datagrid_is_click_command = false;
                $('#${pageFileDir}_list_datagrid').datagrid('selectRow', index);
            } else {
                shareted_easyui_datagrid_columns_icheck_unselect('${pageFileDir}_list_datagrid', index);
            }
        },
        onSelectAll: function (rows) {
            shareted_easyui_datagrid_columns_icheck_select_all('${pageFileDir}_list_datagrid');
        },
        onUnselectAll: function (rows) {
            shareted_easyui_datagrid_columns_icheck_unselect_all('${pageFileDir}_list_datagrid');
        }
    });

    shareted_easyui_datagrid_fill($('#${pageFileDir}_list_datagrid'));
}

function ${pageFileDir}_list_datagrid_del(ids, callBack) {
    if (undefined == ids || (typeof (ids) != 'string' && typeof (ids) != 'number')) {
        var rows = $('#${pageFileDir}_list_datagrid').datagrid('getSelections');
        if (rows.length == 0) {
            shareted_other_hubspot_messenger_show('请选择要操作的记录！');
            return false;
        }

        ids = [];
        for (var rank = 0; rank < rows.length; rank++) {
            ids.push(rows[rank].id);
        }
        ids = ids.join(',');
    }

    shareted_other_hubspot_messenger_confirm('确定删除选择的记录？', function () {
        var param = {
            /*user_ids: ids*/
        };

        $.ajax({
            url: project_path_root + '${deleteUrl}',
            type: 'POST',
            data: param,
            beforeSend: shareted_jquery_ajax_loading('正在加载中……'),
            success: function (result) {
                if (result.code === 0) {
                    shareted_other_hubspot_messenger_show('操作成功！');

                    $('#${pageFileDir}_list_datagrid').datagrid('clearSelections');
                    $('#${pageFileDir}_list_datagrid').datagrid('reload');
                } else {
                    shareted_other_hubspot_messenger_show('操作失败！' + result.msg);
                }
                if(typeof callBack == 'function'){
                    callBack(result)
                }
            },
            error: function (request, status, error) {
                shareted_other_hubspot_messenger_show('操作失败！' + error);
            },
            complete: function (request, status) {
                shareted_jquery_ajax_loadend();
            }
        });
    });
}

function ${pageFileDir}_list_datagrid_set_is_active(ids, is_active) {
    if (undefined == ids || (typeof (ids) != 'string' && typeof (ids) != 'number')) {
        var rows = $('#${pageFileDir}_list_datagrid').datagrid('getSelections');
        if (rows.length == 0) {
            shareted_other_hubspot_messenger_show('请选择要操作的记录！');
            return false;
        }

        ids = [];
        for (var rank = 0; rank < rows.length; rank++) {
            ids.push(rows[rank].id);
        }
        ids = ids.join(',');
    }

    var param = {
        user_ids: ids,
        is_active: is_active
    };

    $.ajax({
        url: project_path_root + '${isActiveUrl}',
        type: 'POST',
        data: param,
        beforeSend: shareted_jquery_ajax_loading('正在处理中……'),
        success: function (result) {
            if (result.code === 0) {
                shareted_other_hubspot_messenger_show('操作成功！');

                $('#${pageFileDir}_list_datagrid').datagrid('reload');
            } else {
                shareted_other_hubspot_messenger_show('操作失败！' + result.msg);
            }
        },
        error: function (request, status, error) {
            shareted_other_hubspot_messenger_show('操作失败！' + error);
        },
        complete: function (request, status) {
            shareted_jquery_ajax_loadend();
        }
    });
}