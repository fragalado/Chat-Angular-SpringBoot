package com.chat.angular.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chat.angular.models.ChatMessage;

@Controller
public class WebSocketController {

    /**
     * Este método recibe los mensajes del cliente y los envía al grupo del chat
     * con el mismo id de la habitación
     * @param roomId El id de la habitación
     * @param message El mensaje que envía el cliente
     * @return
     */
    @MessageMapping("/chat/{roomId}") // Indica que este método será llamado cuando se reciba una petición a /chat/{roomId}
    @SendTo("/topic/{roomId}") // Indica que el método enviará un mensaje a /topic/{roomId}
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message){
        return new ChatMessage(message.getUser(), message.getMessage());
    }
}
