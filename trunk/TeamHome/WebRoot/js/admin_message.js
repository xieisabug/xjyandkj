$(function () {
    $("#message-tab a").click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    $.ajax({
        //获取菜单数据
        url:'message/list.action',
        type:'post',
        success:function (data) {
            var messages = data.messages;
            $('#look_message').data('page', {
                from:1,
                to:messages.length
            });
            var html = [];
            //拼html语句来显示menu
            for (var i in messages) {
                var message = messages[i];
                html.push("<tr>");
                html.push("<td>" + message.id + "</td>");
                html.push("<td>" + message.content + "</td>");
                html.push("<td>" + message.contact + "</td>");
                html.push("<td>" +
                    "<a href='javascript:update(" + message.id + ");' class=\"btn btn-small btn-warning\">修改</a> " +
                    "<a href='javascript:delete_message(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                    "<a href='javascript:star_message(" + message.id + ");' class=\"btn btn-small btn-inverse\">标记为星标</a> " +
                    "</td>");
                html.push("</tr>");
            }
            $("#look_message tbody").html(html);
        }
    });
    $("#message-tab a[href='#star_message']").click(function () {
        $.ajax({
            //获取菜单数据
            url:'message/listStarMessage.action',
            type:'post',
            success:function (data) {
                var messages = data.messages;
                $('#star_message').data('page', {
                    from:1,
                    to:messages.length
                });
                var html = [];
                //拼html语句来显示menu
                for (var i in messages) {
                    var message = messages[i];
                    html.push("<tr>");
                    html.push("<td>" + message.id + "</td>");
                    html.push("<td>" + message.content + "</td>");
                    html.push("<td>" + message.contact + "</td>");
                    html.push("<td>" +
                        "<a href='javascript:delete_star(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                        "</td>");
                    html.push("</tr>");
                }
                $("#star_message tbody").html(html);
            }
        });
    });
});

//下一页留言
function nextMessagePage() {
    var page = $('#look_message').data('page');
    $.ajax({
        //获取菜单数据
        url:'message/list.action',
        type:'post',
        data:{
            from:page.to + 1,
            to:page.to + 9
        },
        success:function (data) {
            if (data.success) {
                var messages = data.messages;
                $('#look_message').data('page', {
                    from:page.to + 1,
                    to:page.to + 10
                });
                var html = [];
                //拼html语句来显示menu
                for (var i in messages) {
                    var message = messages[i];
                    html.push("<tr>");
                    html.push("<td>" + message.id + "</td>");
                    html.push("<td>" + message.content + "</td>");
                    html.push("<td>" + message.contact + "</td>");
                    html.push("<td>" +
                        "<a href='javascript:update_message(" + message.id + ");' class=\"btn btn-small btn-warning\">修改</a> " +
                        "<a href='javascript:delete_message(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                        "<a href='javascript:star_message(" + message.id + ");' class=\"btn btn-small btn-inverse\">标记为星标</a> " +
                        "</td>");
                    html.push("</tr>");
                }
                $("#look_message tbody").html(html);
            }
        }
    });
}

//上一页留言
function previousMessagePage() {
    var page = $('#look_message').data('page');
    $.ajax({
        //获取菜单数据
        url:'message/list.action',
        type:'post',
        data:{
            from:page.from - 10 >= 1 ? page.from - 10 : 1,
            to:page.from - 10 >= 1 ? page.from - 1 : 10
        },
        success:function (data) {
            if (data.success) {
                var messages = data.messages;
                $('#look_message').data('page', {
                    from:page.from - 10 >= 1 ? page.from - 10 : 1,
                    to:page.from - 10 >= 1 ? page.from - 1 : 10
                });
                var html = [];
                //拼html语句来显示menu
                for (var i in messages) {
                    var message = messages[i];
                    html.push("<tr>");
                    html.push("<td>" + message.id + "</td>");
                    html.push("<td>" + message.content + "</td>");
                    html.push("<td>" + message.contact + "</td>");
                    html.push("<td>" +
                        "<a href='javascript:update(" + message.id + ");' class=\"btn btn-small btn-warning\">修改</a> " +
                        "<a href='javascript:delete_message(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                        "<a href='javascript:star_message(" + message.id + ");' class=\"btn btn-small btn-inverse\">标记为星标</a> " +
                        "</td>");
                    html.push("</tr>");
                }
                $("#look_message tbody").html(html);
            }
        }
    });
}

//下一页留言
function nextStarPage() {
    var page = $('#star_message').data('page');
    $.ajax({
        //获取菜单数据
        url:'message/listStarMessage.action',
        type:'post',
        data:{
            from:page.to + 1,
            to:page.to + 9
        },
        success:function (data) {
            if (data.success) {
                var messages = data.messages;
                $('#star_message').data('page', {
                    from:page.to + 1,
                    to:page.to + 10
                });
                var html = [];
                //拼html语句来显示menu
                for (var i in messages) {
                    var message = messages[i];
                    html.push("<tr>");
                    html.push("<td>" + message.id + "</td>");
                    html.push("<td>" + message.content + "</td>");
                    html.push("<td>" + message.contact + "</td>");
                    html.push("<td>" +
                        "<a href='javascript:update_message(" + message.id + ");' class=\"btn btn-small btn-warning\">修改</a> " +
                        "<a href='javascript:delete_message(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                        "<a href='javascript:star_message(" + message.id + ");' class=\"btn btn-small btn-inverse\">标记为星标</a> " +
                        "</td>");
                    html.push("</tr>");
                }
                $("#star_message tbody").html(html);
            }
        }
    });
}

//上一页留言
function previousStarPage() {
    var page = $('#star_message').data('page');
    $.ajax({
        //获取菜单数据
        url:'message/listStarMessage.action',
        type:'post',
        data:{
            from:page.from - 10 >= 1 ? page.from - 10 : 1,
            to:page.from - 10 >= 1 ? page.from - 1 : 10
        },
        success:function (data) {
            if (data.success) {
                var messages = data.messages;
                $('#look_message').data('page', {
                    from:page.from - 10 >= 1 ? page.from - 10 : 1,
                    to:page.from - 10 >= 1 ? page.from - 1 : 10
                });
                var html = [];
                //拼html语句来显示menu
                for (var i in messages) {
                    var message = messages[i];
                    html.push("<tr>");
                    html.push("<td>" + message.id + "</td>");
                    html.push("<td>" + message.content + "</td>");
                    html.push("<td>" + message.contact + "</td>");
                    html.push("<td>" +
                        "<a href='javascript:update(" + message.id + ");' class=\"btn btn-small btn-warning\">修改</a> " +
                        "<a href='javascript:delete_message(" + message.id + ");' class=\"btn btn-small btn-danger\">删除</a> " +
                        "<a href='javascript:star_message(" + message.id + ");' class=\"btn btn-small btn-inverse\">标记为星标</a> " +
                        "</td>");
                    html.push("</tr>");
                }
                $("#star_message tbody").html(html);
            }
        }
    });
}

function update_message(id) {

}

function delete_message(id) {

}

function star_message(id) {

}

function delete_star(id) {

}