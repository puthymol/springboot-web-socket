package com.softvider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {

    @GetMapping("/message")
    public String messagePage() {
        return "message/index.html";
    }

    @GetMapping("/order")
    public String orderPage() {
        return "order/index.html";
    }

}
