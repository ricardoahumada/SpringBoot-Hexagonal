package es.bit.tweeterApp.internal.driven_ports;

import es.bit.tweeterApp.internal.domain.Tweet;

import java.util.List;

public interface IRepoTweetsPort {
    public List<Tweet> findTop10ByTextoContaining(String texto);
    public Tweet save(Tweet tweet);
}
