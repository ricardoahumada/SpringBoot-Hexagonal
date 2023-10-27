package com.banana;

import com.banana.internal.domain.Tweet;
import com.banana.internal.domain.User;
import com.banana.internal.driven_ports.IRepoTweetsPort;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ServiceTweetsPortAdapterTest {

    @Mock
    private IRepoTweetsPort repoPort;

    @InjectMocks
    private ServiceTweetsPortAdapter serviceTweetsPort;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void publishTweet() {
        Tweet newTweet=new Tweet("Hola qué tal",new User(1L,null,null,null));
        when(repoPort.save(any(Tweet.class)))
                .then(invocationOnMock -> {newTweet.setId(1L); return newTweet;})
                .thenReturn(newTweet);
        serviceTweetsPort.publishTweet(newTweet);
        System.out.println("New Tweet:"+newTweet);
        assertTrue(newTweet.getId()>0);
    }

    @Test
    public void getTweets() {
        List<Tweet> tweets = serviceTweetsPort.getTweets("hola");
        System.out.println("Tweets:"+tweets);
        assertNotNull(tweets);
    }
}