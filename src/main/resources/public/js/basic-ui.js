$.get('/selectAllUsers', function (data) {
    for (let i = 0; i < data.length; ++i) {
        $('.result')
            .append(data[i].id + ' ')
            .append(data[i].firstName + ' ')
            .append(data[i].lastName + ' ')
            .append(data[i].username + '<br>');
    }
});

$('#insertUser').click (function () {
    var objToUserTable = {
        firstName: $('#firstname').val(),
        lastName: $('#lastname').val(),
        username: $('#username').val(),
        password: $('#password').val()
    };

    $.ajax('/insertIntoUser', {
        type: 'post',
        data: JSON.stringify(objToUserTable),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) { alert('Receive: ' + data); }
    });

    alert ('Object inserted');
    location.reload();
});