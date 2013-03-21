function createTab(pageFile, jsFile, other) {
    $.get(pageFile,function(data){
        $('#content').html(data);
    });
    if(jsFile) {
        $.getScript('js/'+jsFile);
    }
    if(other){
        if(typeof other == 'object'){
            for(var i in other)
            $.get(other[i]);
        }
    }
}
$(function(){
    $('#menu li').click(function(){
        $('#menu li.active').removeClass('active');
        $(this).addClass('active');
    });
    createTab('admin_count.html','admin_count.js');
 })
