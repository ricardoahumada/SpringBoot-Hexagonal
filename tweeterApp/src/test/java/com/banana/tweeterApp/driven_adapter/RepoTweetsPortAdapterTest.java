package com.banana.tweeterApp.driven_adapter;

import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepoTweetsPortAdapterTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RepoTweetsPortAdapter repoTweetsPortAdapter;

    @Test
    public void findTop10ByTextoContainingValid(){
        List<Tweet> tweets=repoTweetsPortAdapter.findTop10ByTextoContaining("hola");
        System.out.println("Tweets:"+tweets);
        assertNotNull(tweets);
    }

    @Test
    public void saveValid(){
        User autor = new User(1L,"ricardo","r@r.com","ppp");
        Tweet newTweet=new Tweet("Hola qué tal",autor);
        newTweet=repoTweetsPortAdapter.save(newTweet);
        System.out.println("New Tweet:"+newTweet);
        assertTrue(newTweet.getId()>0);
    }

}