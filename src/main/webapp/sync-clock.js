let websocket;
switch (window.location.protocol) {
    case "http:":
        websocket = new WebSocket(`ws://${window.location.host}/ws`);
        break;
    case "https:":
        websocket = new WebSocket(`wss://${window.location.host}/ws`);
        break;
}

const clocks = Array.from(document.getElementsByClassName("Clock"));

websocket.addEventListener("message", function (e) {
    const date = new Date(parseInt(e.data));
    clocks.forEach(clock => clock.innerText = date.toLocaleString());
});