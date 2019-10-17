package es.bit.tweeterApp.websocket;

import es.bit.tweeterApp.websocket.model.TweetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String user = (String) headerAccessor.getSessionAttributes().get("user");
        if(user != null) {
            logger.info("User Disconnected : " + user);

            TweetMessage tweetMessage = new TweetMessage();
            tweetMessage.setType(TweetMessage.MessageType.LEAVE);
            tweetMessage.setAutor(new Long(user));

            messagingTemplate.convertAndSend("/topic/tweets", tweetMessage);
        }
    }
}
