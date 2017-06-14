/**
 * Created by chaoge on 2017/6/13.
 */



$.get('/static/pages/common.html', function(template) {
    $('.elevator').append($(template));
});