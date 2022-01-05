let websocket;
switch (window.location.protocol) {
    case "http:":
        websocket = new WebSocket(`ws://${window.location.host}/ws`);
        break;
    case "https:":
        websocket = new WebSocket(`wss://${window.location.host}/ws`);
        break;
}

const idElement = document.getElementById("id");
const logsElement = document.getElementById("logs");
const toIdElement = document.getElementById("toId");
const messageElement = document.getElementById("message");

websocket.addEventListener("message", function (e) {
    const chatMessage = JSON.parse(e.data);
    console.log(chatMessage);
    const fromId = chatMessage.fromId;
    const toId = chatMessage.toId;
    let log = `from ${fromId} to ${toId} message "${chatMessage.message}"`;
    if (fromId) {
        // normal message;
    } else {
        // server send;
        idElement.innerText = chatMessage.message; // set id to client
    }
    console.log(log);
    const newElement = document.createElement("p");
    newElement.innerText = log;
    logsElement.append(newElement);
});

function send() {
    const chatMessage = { fromId: idElement.innerText, toId: toIdElement.value, message: messageElement.value };
    websocket.send(JSON.stringify(chatMessage));
}
