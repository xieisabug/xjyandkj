$(function () {
    $("#menu-tab a").click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    $.ajax({
        //获取菜单数据
        url:'menu/load.action',
        type:'post',
        success:function (data) {
            var menus = data.list;
            var html = [];
            //拼html语句来显示menu
            for (var i in menus) {
                var menu = menus[i];
                html.push("<tr>");
                html.push("<td>" + menu.id + "</td>");
                html.push("<td>" + menu.code + "</td>");
                html.push("<td>" + menu.name + "</td>");
                html.push("<td>" + menu.link + "</td>");
                html.push("<td>" +
                    "<a href=\"javascript:update(" + menu.id + ")\" class=\"btn btn-small btn-warning\">修改</a>" +
                    "<a href='javascript:delete_menu(" + menu.id + ");' class=\"btn btn-small btn-danger\">删除</a>" +
                    "</td>");
                html.push("</tr>");
            }
            $("#look_menu tbody").html(html);
        }
    });
});
function update(id) {
    $.getJSON('menu/get.action', {id:id}, function (data) {
        $('#menu_dialog_body').empty();
        var menu = data.model;
        var form = '<form class="form-horizontal" id="update_form" method="post"></form>';
        var html = "";
        for (var i in menu) {
            if (i != 'id') {
                html += "<div class=\"control-group\">";
                html += "    <label class=\"control-label\" for=\"" + i + "\">" + i + "</label>";
                html += "    <div class=\"controls\">";
                html += "      <input type=\"text\" id=\"" + i + "\" value='" + menu[i] + "'>";
                html += "    </div>";
                html += "  </div>";
            }
        }
        var button = '<div class="control-group">' +
            '<div class="controls">' +
            '<a href="javascript:update_submit(' + id + ');" class="btn btn-primary" >保存修改</a>' +
            '</div>' +
            '</div>';
        $(form).append(html).append(button).appendTo('#menu_dialog_body');
        $('#menu_dialog').modal('show');
    });
}
function update_submit(id) {
    $.ajax({
        url:'menu/update.action',
        type:'post',
        data:{
            code:$('#code').val(),
            name:$('#name').val(),
            link:$('#link').val(),
            id:id
        },
        success:function (data) {
            if (data.success) {
                $('#menu_msg div.modal-body').html('<p>修改菜单成功！</p>');
                $('#menu_msg').modal('show');
                $('#menu_dialog').modal('hide');
            }
        }
    });
}
function delete_submit(id) {
    $.ajax({
        url:'menu/delete.action',
        type:'post',
        data:{
            id:id
        },
        success:function (data) {
            if (data.success) {
                $('#menu_msg div.modal-body').html('<p>删除菜单成功！</p>');
                $('#menu_msg').modal('show');
                $('#menu_delete').modal('hide');
            }
        }
    });
}
function delete_menu(id) {
    $('#menu_delete').modal('show');
    $('#menu_delete div.modal-footer a.btn-danger').attr('href', 'javascript:delete_submit(' + id + ')');
}
function msg_close() {
    createTab('admin_menu.html', 'admin_menu.js');
    $('#menu_msg').modal('hide');
}
function add_menu() {
    $.ajax({
        url:'menu/add.action',
        type:'post',
        data:{
            code:$('#add_menu #code').val(),
            name:$('#add_menu #name').val(),
            link:$('#add_menu #link').val()
        },
        success:function (data) {
            if (data.success) {
                $('#add_menu div.alert').html('<p>增加菜单成功.</p>');
                $('#add_menu div.alert').show();
            } else {
                $('#add_menu div.alert').html('<p>增加菜单失败.</p>');
                $('#add_menu div.alert').show();
            }
        }
    });
}
function reset() {
    $('#add_menu #code').val('');
    $('#add_menu #name').val('');
    $('#add_menu #link').val('');
}


