let queryParams = new URLSearchParams(window.location.search);
let user = queryParams.get('user');
let userContainer = document.getElementById("userContainer");
userContainer.textContent = user;

let stompClient = '';
initializeSocket();

function initializeSocket() {
    let socket = new SockJS("/websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe("/topic/public", onMessageReceived);

    // Register user to broadcast
    stompClient.send("/app/broadcast.register", {}, JSON.stringify({ user: user }));
}

function sendMessage(event) {
    event.preventDefault();
    let chatInput = document.getElementById("contentInput");
    let message = chatInput.value;

    if (message && stompClient) {
        // Send message to broadcast
        let chatMessage = {
            user: user,
            content: message
        };
        stompClient.send("/app/broadcast.send", {}, JSON.stringify(chatMessage));

        chatInput.value = "";
    }
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    console.log("Message received", message)
    if(message != null) {
        let messageContainer = document.getElementById("messageContainer");
        let messageElement = document.createElement("div");
        messageElement.textContent = message.content;
        messageContainer.appendChild(messageElement);
    }
}

function onError(error) {
    console.log(error)
}

let chatForm = document.getElementById("contentForm");
chatForm.addEventListener("submit", sendMessage);
