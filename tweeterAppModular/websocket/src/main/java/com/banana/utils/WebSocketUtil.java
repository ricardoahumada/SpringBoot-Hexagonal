package com.banana.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class WebSocketUtil {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public boolean sendTweet(Object tweet) throws Exception{
        messagingTemplate.convertAndSend("/topic/tweets", tweet);
        return true;
    }
}
