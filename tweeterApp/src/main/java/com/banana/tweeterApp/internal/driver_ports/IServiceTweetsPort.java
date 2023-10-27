package com.banana.tweeterApp.internal.driver_ports;

import com.banana.tweeterApp.internal.domain.Tweet;

import java.util.List;

public interface IServiceTweetsPort {
    public List<Tweet> getTweets(String theme);
    public Tweet publishTweet(Tweet tweet);
}
