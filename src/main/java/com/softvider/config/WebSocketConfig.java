package com.softvider.config;

import com.softvider.handler.WebSocketMessageHandler;
import com.softvider.handler.WebSocketOrderHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketMessageHandler(), "/websocket/message").setAllowedOrigins("*");
        registry.addHandler(new WebSocketOrderHandler(), "/websocket/order").setAllowedOrigins("*");
    }
}
