package com.banana.internal.driver_ports;

import com.banana.internal.domain.Tweet;

import java.util.List;

public interface IServiceTweetsPort {
    public List<Tweet> getTweets(String theme);
    public Tweet publishTweet(Tweet tweet);
}
