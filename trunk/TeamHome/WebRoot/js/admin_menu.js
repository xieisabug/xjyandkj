$(function(){
    $("#menu-tab a").click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    //todo 正在测试json获取的数据
    $.ajax({
        url:'menu/load.action',
        type:'post',
        success:function(data){
            console.log(data);
        }
    });
});