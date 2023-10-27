package com.banana.tweeterApp.driven_adapter;

import com.banana.tweeterApp.driven_adapter.models.TweetEntity;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoTweets extends CrudRepository<TweetEntity,Long>/*, IRepoTweetsPort*/ {
    public List<TweetEntity> findTop10ByTextoContaining(String tema);
}
