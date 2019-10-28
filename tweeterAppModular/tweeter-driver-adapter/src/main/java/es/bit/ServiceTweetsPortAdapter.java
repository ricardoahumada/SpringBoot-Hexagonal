package es.bit;

import es.bit.internal.domain.Tweet;
import es.bit.internal.driven_ports.IRepoTweetsPort;
import es.bit.internal.driver_ports.IServiceTweetsPort;
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
