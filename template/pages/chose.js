var ${pageFileDir}_chose_option = {};
var ${pageFileDir}_chose_datagrid_search_base = {init : false};
var ${pageFileDir}_chose_datagrid_is_click_command = false;

$(function() {
    ${pageFileDir}_chose_dg_init();
    ${pageFileDir}_chose_datagrid_search_init();
});

function ${pageFileDir}_chose_init(option, search_base, single) {
    ${pageFileDir}_chose_option = option;
   /* if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_user_name == undefined || ${pageFileDir}_chose_option.views.search_user_name == 1) {
        $('[name="${pageFileDir}_chose_div_search_user_name"]').attr('style', 'display:display');
    } else {
        $('[name="${pageFileDir}_chose_div_search_user_name"]').attr('style', 'display:none');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_name == undefined || ${pageFileDir}_chose_option.views.search_name == 1) {
        $('[name="${pageFileDir}_chose_div_search_name"]').attr('style', 'display:display');
    } else {
        $('[name="${pageFileDir}_chose_div_search_name"]').attr('style', 'display:none');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_org_name == undefined || ${pageFileDir}_chose_option.views.search_org_name == 1) {
        $('[name="${pageFileDir}_chose_div_search_org_name"]').attr('style', 'display:display');
    } else {
        $('[name="${pageFileDir}_chose_div_search_org_name"]').attr('style', 'display:none');
    }

    if (undefined == search_base || null == search_base || '' == search_base) {
        ${pageFileDir}_chose_datagrid_search_base = {};
    } else {
        ${pageFileDir}_chose_datagrid_search_base = search_base;
    }*/

    if (single != undefined) {
        ${pageFileDir}_chose_datagrid_init(single);
        if (single) {
            $('#${pageFileDir}_chose_datagrid').datagrid('hideColumn', 'ickeck');
        } else {
            $('#${pageFileDir}_chose_datagrid').datagrid('showColumn', 'ickeck');
        }
    } else {
        ${pageFileDir}_chose_datagrid_init();
    }
}

function ${pageFileDir}_chose_reset() {
    ${pageFileDir}_chose_option = {};
    ${pageFileDir}_chose_datagrid_search_base = {};

    ${pageFileDir}_chose_datagrid_search_reset();
    shareted_easyui_datagrid_reset($('#${pageFileDir}_chose_datagrid'));
}

function ${pageFileDir}_chose_dg_init() {
    $('#${pageFileDir}_chose_dg').dialog({
        modal : true,
        title : '${choseTitle}',
        onClose : ${pageFileDir}_chose_reset
    });
}

function ${pageFileDir}_chose_datagrid_search_init() {
    /*if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_user_name == undefined || ${pageFileDir}_chose_option.views.search_user_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_user_name').textbox({
            value : ''
        });
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_name == undefined || ${pageFileDir}_chose_option.views.search_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_name').textbox({
            value : ''
        });
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_org_name == undefined || ${pageFileDir}_chose_option.views.search_org_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_org_name').textbox({
            value : ''
        });
        shareted_icheck_checkbox_is_init($('#${pageFileDir}_chose_ipt_search_org_id_include_sub'));
    }*/
}

function ${pageFileDir}_chose_datagrid_search_commit() {
    $('#${pageFileDir}_chose_datagrid').datagrid('resize');
    $('#${pageFileDir}_chose_datagrid').datagrid('clearSelections');
    $('#${pageFileDir}_chose_datagrid').datagrid('load', ${pageFileDir}_chose_datagrid_search_get());
}

function ${pageFileDir}_chose_datagrid_search_reset() {
    /*if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_user_name == undefined || ${pageFileDir}_chose_option.views.search_user_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_user_name').textbox('setValue', '');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_name == undefined || ${pageFileDir}_chose_option.views.search_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_name').textbox('setValue', '');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_org_name == undefined || ${pageFileDir}_chose_option.views.search_org_name == 1) {
        $('#${pageFileDir}_chose_ipt_search_org_name').textbox('setValue', '');
        $('#${pageFileDir}_chose_ipt_search_org_id_include_sub').iCheck('uncheck');
    }*/
}

function ${pageFileDir}_chose_datagrid_search_get() {
    var search = {};
    for (var key in ${pageFileDir}_chose_datagrid_search_base) {
        search[key] = ${pageFileDir}_chose_datagrid_search_base[key];
    }

    /*if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_user_name == undefined || ${pageFileDir}_chose_option.views.search_user_name == 1) {
        search.con_user_name = $('#${pageFileDir}_chose_ipt_search_user_name').textbox('getValue');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_name == undefined || ${pageFileDir}_chose_option.views.search_name == 1) {
        search.con_name = $('#${pageFileDir}_chose_ipt_search_name').textbox('getValue');
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.views == undefined || ${pageFileDir}_chose_option.views.search_org_name == undefined || ${pageFileDir}_chose_option.views.search_org_name == 1) {
        search.con_org_name = $('#${pageFileDir}_chose_ipt_search_org_name').textbox('getValue');
        if ($('#${pageFileDir}_chose_ipt_search_org_name').textbox('getValue') != '') {
            search.con_org_id_include_sub = $('#${pageFileDir}_chose_ipt_search_org_id_include_sub').is(':checked') ? 1 : 0;
        }
    }*/

    return search;
}

function ${pageFileDir}_chose_datagrid_columns_get() {
    var columns = [[]];

    columns[0].push(shareted_easyui_datagrid_columns_icheck('${pageFileDir}_chose_datagrid'));
   /* if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.columns == undefined || ${pageFileDir}_chose_option.columns.datagrid_user_name == 1) {
        columns[0].push({
            field : 'user_name',
            title : '用户名',
            width : '180px',
            fill : true,
            halign : project_datagrid_columns_name_halign,
            align : project_datagrid_columns_name_align
        });
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.columns == undefined || ${pageFileDir}_chose_option.columns.datagrid_name == 1) {
        columns[0].push({
            field : 'name',
            title : '姓名',
            width : '120px',
            halign : project_datagrid_columns_name_halign,
            align : project_datagrid_columns_name_align
        });
    }
    if (${pageFileDir}_chose_option == undefined || ${pageFileDir}_chose_option.columns == undefined || ${pageFileDir}_chose_option.columns.datagrid_org_name == 1) {
        columns[0].push({
            field : 'org_name',
            title : '机构',
            width : '120px',
            halign : project_datagrid_columns_name_halign,
            align : project_datagrid_columns_name_align
        });
    }*/

    return columns;
}

function ${pageFileDir}_chose_datagrid_init(single) {
    $('#${pageFileDir}_chose_datagrid').datagrid({
        url : project_path_root + '${listUrl}',
        method : 'POST',
        queryParams : ${pageFileDir}_chose_datagrid_search_get(),
        showHeader : true,
        striped : true,
        singleSelect : single == undefined ? false : single,
        fitColumns : true,
        nowrap : false,
        rownumbers : true,
        rownumberWidth : project_datagrid_columns_row_number_width,
        showFooter : false,
        pagination : true,
        pageSize : 10,
        pageList : [10, 20, 50],
        frozenColumns : [[]],
        columns : ${pageFileDir}_chose_datagrid_columns_get(),
        onBeforeLoad : function(param) {
            if (param.init != undefined) {
                return false;
            }
        },
        loadFilter : function(data) {
            return data;
        },
        onLoadSuccess : function(data) {
            if (data.total < 0) {
                shareted_other_hubspot_messenger_show('获取数据失败！' + data.result.msg);
            } else {
                shareted_easyui_datagrid_columns_icheck_init('${pageFileDir}_chose_datagrid');
                shareted_easyui_datagrid_columns_command_init();
            }
        },
        onLoadError : function() {
            shareted_other_hubspot_messenger_show('获取数据失败！');
        },
        onClickRow : function(index, row) {
        },
        onDblClickRow : function(index, row) {
            $('#${pageFileDir}_chose_datagrid').datagrid('clearSelections');
            $('#${pageFileDir}_chose_datagrid').datagrid('selectRow', index);
        },
        onClickCell : function(index, field, value) {
        },
        onDblClickCell : function(index, field, value) {
        },
        onSelect : function(index, row) {
            if (${pageFileDir}_chose_datagrid_is_click_command) {
                ${pageFileDir}_chose_datagrid_is_click_command = false;
                $('#${pageFileDir}_chose_datagrid').datagrid('unselectRow', index);
            } else {
                shareted_easyui_datagrid_columns_icheck_select('${pageFileDir}_chose_datagrid', index);
            }
        },
        onUnselect : function(index, row) {
            if (${pageFileDir}_chose_datagrid_is_click_command) {
                ${pageFileDir}_chose_datagrid_is_click_command = false;
                $('#${pageFileDir}_chose_datagrid').datagrid('selectRow', index);
            } else {
                shareted_easyui_datagrid_columns_icheck_unselect('${pageFileDir}_chose_datagrid', index);
            }
        },
        onSelectAll : function(rows) {
            shareted_easyui_datagrid_columns_icheck_select_all('${pageFileDir}_chose_datagrid');
        },
        onUnselectAll : function(rows) {
            shareted_easyui_datagrid_columns_icheck_unselect_all('${pageFileDir}_chose_datagrid');
        }
    });

    shareted_easyui_datagrid_fill($('#${pageFileDir}_chose_datagrid'));
}