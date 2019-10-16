package es.bit.tweeterApp.driver_adapter;

import es.bit.tweeterApp.driven_adapter.RepoTweets;
import es.bit.tweeterApp.internal.domain.Tweet;
import es.bit.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import es.bit.tweeterApp.internal.driver_ports.IServiceTweetsPort;
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
