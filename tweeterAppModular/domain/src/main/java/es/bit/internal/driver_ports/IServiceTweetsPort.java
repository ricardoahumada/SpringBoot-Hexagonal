package es.bit.internal.driver_ports;

import es.bit.internal.domain.Tweet;

import java.util.List;

public interface IServiceTweetsPort {
    public List<Tweet> getTweets(String theme);
    public Tweet publishTweet(Tweet tweet);
}
