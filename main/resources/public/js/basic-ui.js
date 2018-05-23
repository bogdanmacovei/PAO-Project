'use strict';

$.get('/selectAllUsers', function (data) {
    for (let i = 0; i < data.length; ++i) {
        $('.result')
            .append(data[i].id + ' ')
            .append(data[i].firstName + ' ')
            .append(data[i].lastName + ' ')
            .append(data[i].username + ' ')
            .append(data[i].img + '<br>');
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

$('#update').click (function () {
    var objToUserTable = {
        id: $('#userid').val(),
        img: $('#image').val()
    };

    $.ajax('/updateImage', {
        type: 'put',
        data: JSON.stringify(objToUserTable),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) { alert('Receive: ' + data); }
    });

    alert ('Image updated!');
    location.reload();
});

$('#delete').click (function () {
    var id = $('#userid2').val();

    $.ajax('/deleteUser/' + id, {
        type: 'delete',
        success: function(data) { alert('Deleted!'); }
    });

    location.reload();
});