var stompClient = null;
var userAvatar = null;

function connect() {
    var socket = new SockJS('/connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        subscribeToAvatarResponses();
        subscribeToRegistrationResponses();
    });
}

function subscribeToAvatarResponses() {
    stompClient.subscribe('/user/queue/avatar', function (catUrl) {
        console.log(catUrl);
        $('#avatar').attr("src", catUrl.body);
        userAvatar = catUrl.body;
    });
}

function subscribeToRegistrationResponses() {
    stompClient.subscribe('/user/queue/registration-response', function (msg) {
        var success = msg.body;
        if (success === 'true') {
            document.getElementById("success-alert").style.display = 'block';
        } else {
            document.getElementById("fail-alert").style.display = 'block';
        }
    })
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendAvatarRequest() {
    stompClient.send("/app/avatar", {}, {});
}

function sendRegistrationRequest(user) {
    stompClient.send("/app/register", {}, JSON.stringify(user));
}

$(document).ready(function () {
    $("#registration-form").on('submit', function(e) {
        e.preventDefault();
        return false;
    });
    connect();

    $("#get-avatar").click(sendAvatarRequest);
    $("#register").click(function () {
        var user = {
            name: $("#reg-name").val(),
            email: $("#reg-email").val(),
            password: $("#reg-password").val(),
            avatar: userAvatar
        };
        sendRegistrationRequest(user);
    });

    $(".close").click(function (event) {
        event.preventDefault();
        var alertId = event.target.getAttribute('close');
        document.getElementById(alertId).style.display = 'none';
    })
});