package com.codingshadows.securemessaging.controller;

import com.codingshadows.securemessaging.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message/{id}")
    @SendTo("/topic/messages/{id}")
    public Message sendMessage(Message message) {
        return message;
    }

}
