package com.softvider.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Random;

@Slf4j
public class WebSocketOrderHandler extends TextWebSocketHandler {
    private final Random rand = new Random();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        // Handle incoming messages
        log.info("Incoming session ID: {}", session.getId());
        String receivedMessage = (String) message.getPayload();
        log.info("Incoming message: {}", receivedMessage);

        // Process the message and send a response to client
        int randNumber = this.rand.nextInt(9) + 1;
        session.sendMessage(new TextMessage(
                String.format("Server received order %s and processing cooking by chief %s",
                        receivedMessage, randNumber)));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Do something when WebSocket connection is established
        log.info("Session ID {} is established", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Do something when WebSocket connection is closed
        log.info("Session ID {} is closed", session.getId());
        log.info("Close status code {}", status.getCode());
        log.info("Close status reason {}", status.getReason());
    }
}
