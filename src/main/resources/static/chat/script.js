let queryParams = new URLSearchParams(window.location.search);
let sender = queryParams.get('sender');
let recipient = queryParams.get('recipient');
let senderContainer = document.getElementById("senderContainer");
let recipientContainer = document.getElementById("recipientContainer");
senderContainer.textContent = sender;
recipientContainer.textContent = recipient;

let stompClient = '';
initializeSocket();

function initializeSocket() {
    let socket = new SockJS("/websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    // Subscribe message that receiving from recipient (E.g. BobAlice, it will subscribe only message receiving from Bob to Alice)
    let path = "/user/" + recipient + sender + "/messages";
    stompClient.subscribe(path, onMessageReceived, onError);
}

function sendMessage(event) {
    event.preventDefault();
    let chatInput = document.getElementById("contentInput");
    let message = chatInput.value;

    if (message && stompClient) {
        // Send message
        let chatMessage = {
            sender: sender,
            recipient: recipient,
            content: message
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        appendMessage(message);
        chatInput.value = "";
    }
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    console.log("Message received", message)
    if(message != null) {
        let messageContainer = document.getElementById("messageContainer");
        let messageElement = document.createElement("div");
        messageElement.innerHTML = "<b>" + message.sender + "</b><br>" + message.content;
        messageContainer.appendChild(messageElement);
    }
}

function onError(error) {
    console.log(error)
}

function appendMessage(message) {
    let messageContainer = document.getElementById("messageContainer");
    let messageElement = document.createElement("div");
    messageElement.innerHTML = "<b>" + sender + "</b><br>" + message;
    messageElement.style.textAlign = "right"
    messageElement.style.color = '#3273a8'
    messageContainer.appendChild(messageElement);
}

let chatForm = document.getElementById("contentForm");
chatForm.addEventListener("submit", sendMessage);
