package com.banana;

import com.banana.internal.domain.Tweet;
import com.banana.internal.driven_ports.IRepoTweetsPort;
import com.banana.internal.driver_ports.IServiceTweetsPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ServiceTweetsPortAdapter implements IServiceTweetsPort {

    private IRepoTweetsPort repoTweetsPort;

    public ServiceTweetsPortAdapter(IRepoTweetsPort repoTweetsPort){
        this.repoTweetsPort=repoTweetsPort;
    }

    @Override
    @Transactional
    public Tweet publishTweet(Tweet tweet) {

        return repoTweetsPort.save(tweet);
    }

    @Override
    @Transactional
    public List<Tweet> getTweets(String theme) {

        return repoTweetsPort.findTop10ByTextoContaining(theme);
    }
}
