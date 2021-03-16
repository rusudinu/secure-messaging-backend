var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('sessionID');
        console.log('Connected: ' + myParam);
        let domain = "/topic/greetings/" + myParam;
        stompClient.subscribe(domain, function (greeting) {
            showGreeting(JSON.parse(greeting.body).messageText);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('sessionID');
    console.log('sending: ' + myParam);
    let domain = "/app/hello/" + myParam;
    stompClient.send(domain, {}, JSON.stringify({'messageText': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});