package com.codingshadows.securemessaging.controller;

import com.codingshadows.securemessaging.data.ConnectionsMap;
import com.codingshadows.securemessaging.model.Message;
import com.codingshadows.securemessaging.model.MutableInt;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Controller
public class WebSocketsListener {

    /*
     *
     * NCCSRV = NEW CLIENT CONNECTED SERVER
     * CDFSRV = CLIENT DISCONNECTED FROM SERVER
     *
     */

    final
    SimpMessagingTemplate template;

    public WebSocketsListener(SimpMessagingTemplate template) {
        this.template = template;
    }

    @EventListener
    public void onSubscribedEvent(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        this.template.convertAndSend(headers.getDestination(), new Message("NCCSRV", "server", false, null));
        ConnectionsMap.connectionsMap.put(headers.getSessionId(), headers.getDestination());
        MutableInt currentCounter = ConnectionsMap.connectionsCounterMap.get(headers.getDestination());
        if (currentCounter != null)
            currentCounter.increment();
        else
            ConnectionsMap.connectionsCounterMap.put(headers.getDestination(), new MutableInt());
    }

    @EventListener
    public void sessionDisconnectEvent(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String destination = ConnectionsMap.connectionsMap.get(headers.getSessionId());
        this.template.convertAndSend(destination, new Message("CDFSRV", "server", false, null));
        MutableInt currentCounter = ConnectionsMap.connectionsCounterMap.get(destination);
        if (currentCounter != null) {
            currentCounter.decrement();
            if (currentCounter.getValue() == 0) {
                ConnectionsMap.connectionsCounterMap.remove(destination);
            }
        }
        ConnectionsMap.connectionsMap.remove(headers.getSessionId());
    }
}
