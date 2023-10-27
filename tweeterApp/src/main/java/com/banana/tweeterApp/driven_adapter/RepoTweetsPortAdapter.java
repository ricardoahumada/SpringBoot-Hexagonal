package com.banana.tweeterApp.driven_adapter;

import com.banana.tweeterApp.driven_adapter.maps.TweetMapper;
import com.banana.tweeterApp.driven_adapter.models.TweetEntity;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RepoTweetsPortAdapter implements IRepoTweetsPort {

    @Autowired
    private RepoTweets repoTweets;

    @Override
    public List<Tweet> findTop10ByTextoContaining(String texto) {
        List<TweetEntity> tweetEntities= repoTweets.findTop10ByTextoContaining(texto);
        return TweetMapper.INSTANCE.tweetEntitiesToTweets(tweetEntities);
    }

    @Override
    public Tweet save(Tweet tweet) {
        TweetEntity tweetEntity=repoTweets.save(TweetMapper.INSTANCE.tweetToTweetEntity(tweet));
        return TweetMapper.INSTANCE.tweetEntityToTweet(tweetEntity);
    }
}
