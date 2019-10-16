package es.bit.tweeterApp.driven_adapter;

import es.bit.tweeterApp.driven_adapter.models.TweetEntity;
import es.bit.tweeterApp.internal.domain.Tweet;
import es.bit.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoTweets extends CrudRepository<TweetEntity,Long>/*, IRepoTweetsPort*/ {
    public List<TweetEntity> findTop10ByTextoContaining(String tema);
}
