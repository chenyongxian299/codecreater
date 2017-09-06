var ${pageFileDir}_add_option = {};

$(function() {
    ${pageFileDir}_add_init();
});

function ${pageFileDir}_add_init() {
    $('#${pageFileDir}_add_dg').dialog({
        modal : true,
        title : '${addTitle}',
        onClose : ${pageFileDir}_add_reset,
        buttons : [
            {
                text : '确定',
                handler : ${pageFileDir}_add_commit
            }
        ]
    });
    $('#${pageFileDir}_add_frm').form({
        novalidate : true
    });

    /*$('#${pageFileDir}_add_ipt_user_name').textbox({
        value : '',
        required : true
    });
    shareted_icheck_checkbox_is_active_init($('#${pageFileDir}_add_ipt_is_active'));
    $('#${pageFileDir}_add_ipt_is_active').iCheck('check');
    shareted_easyui_textbox_ipt_memo_init($('#${pageFileDir}_add_ipt_memo'), {}, false);*/
}

function ${pageFileDir}_add_reset() {
    ${pageFileDir}_add_option = {};

    $('#${pageFileDir}_add_ipt_user_name').textbox('setValue', '');
    $('#${pageFileDir}_add_ipt_is_active').iCheck('check');
    $('#${pageFileDir}_add_ipt_memo').textbox('setValue', '');
}

function ${pageFileDir}_add_dg_show(option) {
    ${pageFileDir}_add_option = option;

    shareted_easyui_dialog_show($('#${pageFileDir}_add_dg'));
}

function ${pageFileDir}_add_commit() {
    if ($('#${pageFileDir}_add_frm').form('enableValidation').form('validate')) {
        var param = {
            /*user_name : $('#${pageFileDir}_add_ipt_user_name').textbox('getValue'),
            is_active : $('#${pageFileDir}_add_ipt_is_active').is(':checked') ? 1 : 0,
            memo : $('#${pageFileDir}_add_ipt_memo').textbox('getValue')*/
        };

        $.ajax({
            url : project_path_root + '${addUrl}',
            type : 'POST',
            data : param,
            beforeSend : shareted_jquery_ajax_loading('正在处理中……'),
            success : function(result) {
                if (result.code === 0) {
                    shareted_other_hubspot_messenger_show('操作成功！');

                    $('#${pageFileDir}_list_datagrid').datagrid('reload');

                    $('#${pageFileDir}_add_dg').dialog('close');
                } else {
                    shareted_other_hubspot_messenger_show('操作失败！' + result.msg);
                }
            },
            error : function(request, status, error) {
                shareted_other_hubspot_messenger_show('操作失败！' + error);
            },
            complete : function(request, status) {
                shareted_jquery_ajax_loadend();
            }
        });
    }
}