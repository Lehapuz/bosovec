let name = 'vlad'
var is = true
console.log(is)


$(document).on('click', '.post-link', function(){
        var link = $(this);
        var postId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/' + postId,
            success: function(response)
            {
               var code = '<span>Автор:' + response.author + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Пост не найдено!');
                }
            }
        });
        return false;
    });