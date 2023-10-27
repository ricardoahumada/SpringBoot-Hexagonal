package com.banana;

import com.banana.model.TweetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class TweetWSController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @MessageMapping("/tweets/{theme}")
    @SendTo("/topic/tweets/{theme}")
    public TweetMessage themeChannel(@Payload TweetMessage tweetMessage, @DestinationVariable String theme, SimpMessageHeaderAccessor headerAccessor) {
        logger.info(tweetMessage+"::"+theme);
        headerAccessor.getSessionAttributes().put(theme, theme);
        return tweetMessage;
    }
}
