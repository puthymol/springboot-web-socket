package com.softvider.controller;

import com.softvider.data.BroadcastMessage;
import com.softvider.utils.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BroadcastController {

    @GetMapping("/broadcast")
    public String broadcastPage() {
        return "broadcast/index.html";
    }

    @MessageMapping("/broadcast.register")
    @SendTo("/topic/public")
    public BroadcastMessage register(@Payload BroadcastMessage message, SimpMessageHeaderAccessor headerAccessor) {
        // Do something when user register.
        log.info("Incoming message: {}", AppUtil.toJSON(message));
        message.setContent(message.getUser() + " joint Broadcast.");
        return message;
    }

    @MessageMapping("/broadcast.send")
    @SendTo("/topic/public")
    public BroadcastMessage sendMessage(@Payload BroadcastMessage message) {
        // Do something with message before push to registered client
        log.info("Incoming message: {}", AppUtil.toJSON(message));
        message.setContent(message.getUser() + ": " + message.getContent());
        return message;
    }

}
