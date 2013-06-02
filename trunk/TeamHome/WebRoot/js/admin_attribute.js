$(function () {
    $("#attribute-tab a").click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    $.ajax({
        //获取菜单数据
        url:'attr/load.action',
        type:'post',
        success:function (data) {
            var attrs = data.list;
            var html = [];
            //拼html语句来显示menu
            for (var i in attrs) {
                var attr = attrs[i];
                html.push("<tr>");
                html.push("<td>" + attr.id + "</td>");
                html.push("<td>" + attr.name + "</td>");
                html.push("<td>" + attr.value + "</td>");
                html.push("<td>" +
                    "<a href=\"javascript:update(" + attr.id + ")\" class=\"btn btn-small btn-warning\">修改</a>" +
                    "<a href='javascript:delete_attr(" + attr.id + ");' class=\"btn btn-small btn-danger\">删除</a>" +
                    "</td>");
                html.push("</tr>");
            }
            $("#attr_manage tbody").html(html);
        }
    });
});
function update(id) {
    $.getJSON('attr/get.action', {id:id}, function (data) {
        $('#attr_dialog_body').empty();
        var attr = data.model;
        var form = '<form class="form-horizontal" id="update_form" method="post"></form>';
        var html = "";
        for (var i in attr) {
            if (i != 'id') {
                html += "<div class=\"control-group\">";
                html += "    <label class=\"control-label\" for=\"" + i + "\">" + i + "</label>";
                html += "    <div class=\"controls\">";
                html += "      <input type=\"text\" id=\"" + i + "\" value='" + attr[i] + "'>";
                html += "    </div>";
                html += "  </div>";
            }
        }
        var button = '<div class="control-group">' +
            '<div class="controls">' +
            '<a href="javascript:update_submit(' + id + ');" class="btn btn-primary" >保存修改</a>' +
            '</div>' +
            '</div>';
        $(form).append(html).append(button).appendTo('#attr_dialog_body');
        $('#attr_dialog').modal('show');
    });
}
function update_submit(id) {
    $.ajax({
        url:'attr/update.action',
        type:'post',
        data:{
            value:$('#value').val(),
            name:$('#name').val(),
            id:id
        },
        success:function (data) {
            if (data.success) {
                $('#attr_msg div.modal-body').html('<p>修改菜单成功！</p>');
                $('#attr_msg').modal('show');
                $('#attr_dialog').modal('hide');
            }
        }
    });
}
function delete_submit(id) {
    $.ajax({
        url:'attr/delete.action',
        type:'post',
        data:{
            id:id
        },
        success:function (data) {
            if (data.success) {
                $('#attr_msg div.modal-body').html('<p>删除菜单成功！</p>');
                $('#attr_msg').modal('show');
                $('#attr_delete').modal('hide');
            }
        }
    });
}
function delete_attr(id) {
    $('#attr_delete').modal('show');
    $('#attr_delete div.modal-footer a.btn-danger').attr('href', 'javascript:delete_submit(' + id + ')');
}
function msg_close() {
    createTab('admin_attribute.html', 'admin_attribute.js');
    $('#attr_msg').modal('hide');
}
function add_attr() {
    $.ajax({
        url:'attr/add.action',
        type:'post',
        data:{
            name:$('#add_attr #name').val(),
            value:$('#add_attr #value').val()
        },
        success:function (data) {
            if (data.success) {
                $('#add_attr div.alert').html('<p>增加菜单成功.</p>');
                $('#add_attr div.alert').show();
            } else {
                $('#add_attr div.alert').html('<p>增加菜单失败.</p>');
                $('#add_attr div.alert').show();
            }
        }
    });
}
function reset() {
    $('#add_attr #name').val('');
    $('#add_attr #value').val('');
}


