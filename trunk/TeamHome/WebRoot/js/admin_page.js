$(function () {
    $("#page-tab a").click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $.ajax({
        //获取菜单数据
        url:'page/indexCss.action',
        type:'post',
        success:function (data) {
            $('#indexCss').text(data.css);
        }
    });
    $("#page-tab a[href='#star_message']").click(function () {
        if ($('#indexHtml').text() == '') {
            $.ajax({
                //获取菜单数据
                url:'page/indexHtml.action',
                type:'post',
                success:function (data) {
                    $('#indexHtml').text(data.html);
                }
            });
        }
    });
});
function changeIndexCss() {
    var indexCss = $('#indexCss').val();
    $.ajax({
        url:'page/changeIndexCss.action',
        type:'post',
        data:{
            css : indexCss
        },
        success:function (data) {
            //todo 成功提示
        }
    });
}
function changeIndexHtml() {
    var indexHtml = $('#indexHtml').val();
    $.ajax({
        url:'page/changeIndexHtml.action',
        type:'post',
        data:{
            html : indexHtml
        },
        success:function (data) {
            //todo 成功提示
        }
    });
}
