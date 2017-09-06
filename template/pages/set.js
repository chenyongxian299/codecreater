var ${pageFileDir}_set_option = {};
var ${pageFileDir}_set_user_id;

$(function() {
    ${pageFileDir}_set_init();
});

function ${pageFileDir}_set_init() {
    $('#${pageFileDir}_set_dg').dialog({
        modal : true,
        title : '编辑',
        onClose : ${pageFileDir}_set_reset,
        buttons : [{
            text : '确定',
            handler : ${pageFileDir}_set_commit
        }, {
            text : '删除',
            handler : ${pageFileDir}_set_del
        }]
    });
    $('#${pageFileDir}_set_frm').form({
        novalidate : true
    });

   /* $('#${pageFileDir}_set_ipt_user_name').textbox({
        value : '',
        required : true
    });
    shareted_icheck_checkbox_is_active_init($('#${pageFileDir}_set_ipt_is_active'));
    shareted_easyui_textbox_ipt_memo_init($('#${pageFileDir}_set_ipt_memo'), {}, false);*/
}

function ${pageFileDir}_set_reset() {
    ${pageFileDir}_set_option = {};
    ${pageFileDir}_set_user_id = null;

   /* $('#${pageFileDir}_set_ipt_user_name').textbox('setValue', '');
    $('#${pageFileDir}_set_ipt_is_active').iCheck('uncheck');
    $('#${pageFileDir}_set_ipt_memo').textbox('setValue', '');*/
}

function ${pageFileDir}_set_dg_show(option, user_id) {
    ${pageFileDir}_set_option = option;
    ${pageFileDir}_set_user_id = user_id;

    var param = {
        user_id : ${pageFileDir}_set_user_id
    };

    $.ajax({
        url : project_path_root + '${getUrl}',
        type : 'POST',
        data : param,
        beforeSend : shareted_jquery_ajax_loading('正在处理中……'),
        success : function(result) {
            if (result.code === 0) {
                var data = result.data;

                $('#${pageFileDir}_set_ipt_user_name').textbox('setValue', data.user_name);
                if (1 === data.is_active) {
                    $('#${pageFileDir}_set_ipt_is_active').iCheck('check');
                }
                $('#${pageFileDir}_set_ipt_memo').textbox('setValue', data.memo);

                shareted_easyui_dialog_show($('#${pageFileDir}_set_dg'));
            } else {
                shareted_other_hubspot_messenger_show('获取数据失败！' + result.msg);
            }
        },
        error : function(request, status, error) {
            shareted_other_hubspot_messenger_show('获取数据失败！' + error);
        },
        complete : function(request, status) {
            shareted_jquery_ajax_loadend();
        }
    });
}

function ${pageFileDir}_set_commit() {
    if ($('#${pageFileDir}_set_frm').form('enableValidation').form('validate')) {
        var param = {
          /*  id : ${pageFileDir}_set_user_id,
            user_name : $('#${pageFileDir}_set_ipt_user_name').textbox('getValue'),
            is_active : $('#${pageFileDir}_set_ipt_is_active').is(':checked') ? 1 : 0,
            memo : $('#${pageFileDir}_set_ipt_memo').textbox('getValue')*/
        };

        $.ajax({
            url : project_path_root + '${setUrl}',
            type : 'POST',
            data : param,
            beforeSend : shareted_jquery_ajax_loading('正在处理中……'),
            success : function(result) {
                if (result.code === 0) {
                    shareted_other_hubspot_messenger_show('操作成功！');

                    $('#${pageFileDir}_list_datagrid').datagrid('reload');

                    $('#${pageFileDir}_set_dg').dialog('close');
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

function ${pageFileDir}_set_del() {
    ${pageFileDir}_list_datagrid_del(${pageFileDir}_set_user_id,function(result){
        $('#${pageFileDir}_set_dg').dialog('close');
    })
}