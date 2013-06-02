$(function(){
    $('#menu').mouseenter(function(){
        $(this).stop().animate({
            left:0
        },200);
    }).mouseleave(function(){
        $(this).stop().animate({
            left:-70
        },600)
    });

    $.ajax({
        url:'attr/map.action',
        type:'post',
        success:function(data){
            var map = data.map;
            $('#main_title').html(map['main_title']);
            $('#second_title').html(map['second_title']);
            var item1 = $('#item div.span4:eq(0)');
            var item2 = $('#item div.span4:eq(1)');
            var item3 = $('#item div.span4:eq(2)');
            item1.find('div.goodItem_title').html(map['good_item1_title']);
            item1.find('div.goodItem_content').html(map['good_item1_content']);

            item2.find('div.goodItem_title').html(map['good_item2_title']);
            item2.find('div.goodItem_content').html(map['good_item2_content']);

            item3.find('div.goodItem_title').html(map['good_item3_title']);
            item3.find('div.goodItem_content').html(map['good_item3_content']);

            $('#other').html(map['other']);
        }
    });

    $.ajax({
        url:'menu/load.action',
        type:'post',
        success:function(data){
            var menus = data.list;
            var html = '';
            for(var i in menus){
                var menu = menus[i];
                html += "<li><a href='"+menu.link+"' style='background:url(img/menu/"+menu.name+".png)'></a></li>";
            }
            $('#menu ul').html(html);
        }
    });
})

function message(){
    var message = $('#summary input.span7').val();
    $.ajax({
        url:'message/add.action',
        type:'post',
        data:{
            content : message,
            contact:''
        },
        success:function(data){
            if(data.success){
                $('#contact_win').modal('show');
                $('#message_id').val(data.model.id);
                $('#message_content').val(message);
                $('#summary input.span7').val('');
            }
        }
    });
}

function contact(){
    var id = $('#message_id').val();
    var content = $('#message_content').val();
    var contact = $('#contact_win input.input-block-level').val();
    $.ajax({
        url:'message/update.action',
        type:'post',
        data:{
            id : id,
            content:content,
            contact:contact
        },
        success:function(data){
            if(data.success){
                $('#contact_win').modal('hide');
                $('#message_id').val('');
                $('#message_content').val('');
                $('#contact_win input.input-block-level').val('');
            }
        }
    });
}