package com.codingshadows.securemessaging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/secure-messaging-websocket-endpoint").setAllowedOriginPatterns("*").withSockJS().setClientLibraryUrl("/webjars/sockjs-client/1.0.2/sockjs.min.js");
    }

//    @EventListener
//    public void onDisconnectEvent(SessionUnsubscribeEvent event) {
//        //LOGGER.debug("Client with username {} disconnected", event.getUser());
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        System.out.println(headers.getDestination());
//    }
//
//    @EventListener
//    public void onUnsubscribedEvent(SessionUnsubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        System.out.println(headers.getDestination());
//    }
//    @EventListener
//    public void onConnectedEvent(SessionConnectedEvent event) {
//        //listener for client connects
//        System.out.println(event.toString());
//    }
}