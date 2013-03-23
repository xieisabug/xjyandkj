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
                    "<a href=\"#\" class=\"btn btn-small btn-danger\">删除</a>" +
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
        var form = '<form class="form-horizontal" action="menu/update.action" method="post"></form>';
        var html = "";
        for (var i in menu) {
            if (i != 'id') {
                html+="<div class=\"control-group\">";
                html+="    <label class=\"control-label\" for=\"" + i + "\">" + i + "</label>";
                html+="    <div class=\"controls\">";
                html+="      <input type=\"text\" id=\"" + i + "\" value='" + menu[i] + "'>";
                html+="    </div>";
                html+="  </div>";
            }
        }
        var button = '<div class="control-group">' +
            '<div class="controls">' +
            '<input type="submit" class="btn btn-primary" value="保存修改">' +
            '</div>' +
            '</div>';
        $(form).append(html).append(button).appendTo('#menu_dialog_body');
        $('#menu_dialog').modal('show');
    });
}