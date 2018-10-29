$(document).ready(function () {
    $("#auth-form").on('submit', function(e) {
        e.preventDefault();
        return false;
    });

    $('#auth').click(function (event) {
        event.preventDefault();
        var credentials = {
            email: $('#email').val(),
            password: $('#password').val()
        };
        fetch('/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        }).then(function (response) {
            response.json().then(function (data) {
                if (data.success === false) {
                    document.getElementById('fail-alert-msg').innerText = data.errorMsg;
                    document.getElementById("fail-alert").style.display = 'block';
                } else {
                    console.log(data.token);
                    localStorage.setItem("token", data.token);
                }
            })
        })
    });

    $(".close").click(function (event) {
        event.preventDefault();
        var alertId = event.target.getAttribute('close');
        document.getElementById(alertId).style.display = 'none';
    })
});