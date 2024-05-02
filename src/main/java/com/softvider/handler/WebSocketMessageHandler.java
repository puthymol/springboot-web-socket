package com.softvider.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class WebSocketMessageHandler extends TextWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        // Handle incoming messages
        log.info("Incoming session ID: {}", session.getId());
        String receivedMessage = (String) message.getPayload();
        log.info("Incoming message: {}", receivedMessage);

        // Process the message and send a response to client
        session.sendMessage(new TextMessage(
                String.format("Server received %s and responded back on %s",
                        receivedMessage, LocalDateTime.now())));
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
