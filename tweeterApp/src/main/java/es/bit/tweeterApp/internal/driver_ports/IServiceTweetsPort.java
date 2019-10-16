package es.bit.tweeterApp.internal.driver_ports;

import es.bit.tweeterApp.internal.domain.Tweet;

import java.util.List;

public interface IServiceTweetsPort {
    public List<Tweet> getTweets(String theme);
    public Tweet publishTweet(Tweet tweet);
}
