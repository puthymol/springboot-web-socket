let order = new WebSocket("ws://localhost:8080/websocket/order");

order.onopen = function(event) {
    console.log("WebSocket connection established.");
};

order.onmessage = function(event) {
    let message = event.data;
    console.log("Message received from server " + message)
    displayMessage(message);
};

order.onclose = function(event) {
    console.log("WebSocket connection closed.");
};

order.onerror = function(error) {
    console.error("WebSocket error:", error);
};

let orderForm = document.getElementById("orderForm");
orderForm.addEventListener("submit", sendMessage);

function sendMessage(event) {
    event.preventDefault();
    let orderInput = document.getElementById("orderInput");
    let orderValue = orderInput.value;
    order.send(orderValue);
    orderInput.value = "";
}

function displayMessage(message) {
    let messageContainer = document.getElementById("messageContainer");
    let messageElement = document.createElement("div");
    messageElement.textContent = message;
    messageContainer.appendChild(messageElement);
}
