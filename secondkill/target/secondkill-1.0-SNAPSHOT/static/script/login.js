$('#login').click(function(event) {
    event.preventDefault();
    $('#login-panel').show();
    $('#register-panel').hide();
});
$('#register').click(function(event) {
    event.preventDefault();
    $('#login-panel').hide();
    $('#register-panel').show();
});

$('#login-button').click(function(event) {
    event.preventDefault();
    var username = $("#login-panel input[name='username']").val();
    var password = $("#login-panel input[name='password']").val();
    if (username == "") {
        $('#login-error-lable').html('用户名不能为空');
        return;
    }
    if (password == "") {
        $('#login-error-lable').html('密码不能为空');
        return;
    }
    $.ajax({
        url:'/login',
        data:{'name':username, 'password':password},
        type: 'POST',
        success : function(response) {
            if (200 == response.status) {
                window.location.href = '/';
            } else {
                $('#login-error-lable').html('用户名或密码错误');
            }
        }
    });
});

$('#register-button').click(function(event) {
    event.preventDefault();
    var username = $("#register-panel input[name='username']").val();
    var password = $("#register-panel input[name='password']").val();
    var confirmpw = $("#register-panel input[name='confirmpw']").val();
    if (username == "") {
        $('#register-error-lable').html('用户名不能为空');
        return;
    }
    if (password == "") {
        $('#register-error-lable').html('密码不能为空');
        return;
    }
    if (password != confirmpw) {
        $('#register-error-lable').html('确认密码不正确');
        return;
    }
    $.ajax({
        url:'/register',
        data:{'name':username, 'password':password},
        type: 'POST',
        success : function(response) {
            if (200 == response.status) {
                window.location.href('/');
            } else {
                $('#register-error-lable').html('用户名或密码错误');
            }
        }
    });
});