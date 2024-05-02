let message = new WebSocket("ws://localhost:8080/websocket/message");

message.onopen = function(event) {
    console.log("WebSocket connection established.");
};

message.onmessage = function(event) {
    let message = event.data;
    console.log("Message received from server " + message)
    displayMessage(message);
};

message.onclose = function(event) {
    console.log("WebSocket connection closed.");
};

message.onerror = function(error) {
    console.error("WebSocket error:", error);
};

let messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", sendMessage);

function sendMessage(event) {
    event.preventDefault();
    let messageInput = document.getElementById("messageInput");
    let messageValue = messageInput.value;
    message.send(messageValue);
    messageInput.value = "";
}

function displayMessage(message) {
    let messageContainer = document.getElementById("messageContainer");
    let messageElement = document.createElement("div");
    messageElement.textContent = message;
    messageContainer.appendChild(messageElement);
}
