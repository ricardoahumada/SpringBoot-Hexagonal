package com.banana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetMessage {
    private String texto;
    private Date fecha;
    private Long autor;
    private MessageType type;

    public enum MessageType {
        TWEET,
        JOIN,
        LEAVE
    }

}
