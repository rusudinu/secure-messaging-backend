package com.codingshadows.securemessaging.controller;

import com.codingshadows.securemessaging.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings/{id}")
    public Message greeting(Message message) {
        System.out.println("got message");
        return message;
    }

}
