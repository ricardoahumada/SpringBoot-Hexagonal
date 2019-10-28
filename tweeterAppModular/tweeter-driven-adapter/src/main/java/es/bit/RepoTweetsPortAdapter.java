package es.bit;

import es.bit.internal.domain.Tweet;
import es.bit.internal.driven_ports.IRepoTweetsPort;
import es.bit.maps.TweetEntityMapper;
import es.bit.models.TweetEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RepoTweetsPortAdapter implements IRepoTweetsPort {

    @Autowired
    private RepoTweets repoTweets;

    @Override
    public List<Tweet> findTop10ByTextoContaining(String texto) {
        List<TweetEntity> tweetEntities= repoTweets.findTop10ByTextoContainingIgnoreCase(texto);
        return TweetEntityMapper.INSTANCE.tweetEntitiesToTweets(tweetEntities);
    }

    @Override
    public Tweet save(Tweet tweet) {
        TweetEntity tweetEntity=repoTweets.save(TweetEntityMapper.INSTANCE.tweetToTweetEntity(tweet));
        return TweetEntityMapper.INSTANCE.tweetEntityToTweet(tweetEntity);
    }
}
