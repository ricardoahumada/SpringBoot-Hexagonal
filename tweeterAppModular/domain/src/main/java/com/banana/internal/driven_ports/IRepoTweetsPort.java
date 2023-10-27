package com.banana.internal.driven_ports;

import com.banana.internal.domain.Tweet;

import java.util.List;

public interface IRepoTweetsPort {
    public List<Tweet> findTop10ByTextoContaining(String texto);
    public Tweet save(Tweet tweet);
}
