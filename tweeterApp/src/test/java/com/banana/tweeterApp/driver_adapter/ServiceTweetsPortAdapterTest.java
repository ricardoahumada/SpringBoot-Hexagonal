package com.banana.tweeterApp.driver_adapter;

import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.domain.User;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import com.banana.tweeterApp.internal.driver_ports.IServiceTweetsPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTweetsPortAdapterTest {

    @Autowired
    private IServiceTweetsPort serviceTweetsPort;

    @Test
    public void publishTweet() {
        Tweet newTweet=new Tweet("Hola qué tal",new User(1L,null,null,null));
        newTweet=serviceTweetsPort.publishTweet(newTweet);
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