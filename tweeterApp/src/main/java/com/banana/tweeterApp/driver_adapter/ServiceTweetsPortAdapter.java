package com.banana.tweeterApp.driver_adapter;

import com.banana.tweeterApp.driven_adapter.RepoTweets;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import com.banana.tweeterApp.internal.driver_ports.IServiceTweetsPort;
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
