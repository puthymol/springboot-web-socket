package com.softvider.controller;

import com.softvider.data.ChatMessage;
import com.softvider.utils.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat/index.html";
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage message) {
        log.info("Incoming message: {}", AppUtil.toJSON(message));
        String sender = message.getSender();
        String recipient = message.getRecipient();
        if (recipient != null && !recipient.isEmpty()) {
            // Send message to recipient (E.g. AliceBob, the message will send from Alice to Bob)
            messagingTemplate.convertAndSendToUser(sender + recipient, "/messages", message);
        }
    }
}
