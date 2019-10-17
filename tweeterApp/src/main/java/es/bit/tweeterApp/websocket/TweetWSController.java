package es.bit.tweeterApp.websocket;

import es.bit.tweeterApp.websocket.model.TweetMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class TweetWSController {

    @MessageMapping("/tweets.sendMessage")
    @SendTo("/topic/tweets")
    public TweetMessage sendMessage(@Payload TweetMessage tweetMessage) {
        return tweetMessage;
    }

    @MessageMapping("/tweets.addUser")
    @SendTo("/topic/tweets")
    public TweetMessage addUser(@Payload TweetMessage tweetMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("user", tweetMessage.getAutor());
        return tweetMessage;
    }
}
