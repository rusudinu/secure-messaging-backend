package com.codingshadows.securemessaging.controller;

import com.codingshadows.securemessaging.data.ConnectionsMap;
import com.codingshadows.securemessaging.model.Message;
import com.codingshadows.securemessaging.model.MutableInt;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @MessageMapping("/message/{id}")
    @SendTo("/topic/messages/{id}")
    public Message sendMessage(Message message) {
        return message;
    }

    @GetMapping("/room/{id}/connected-count")
    @ResponseBody
    public MutableInt getUsersCount(@PathVariable String id) {
        System.out.println(ConnectionsMap.connectionsCounterMap.get("/topic/messages/" + id).getValue());
        return ConnectionsMap.connectionsCounterMap.get("/topic/messages/" + id);
    }
}
