package es.bit.tweeterApp.driven_adapter;

import es.bit.tweeterApp.driven_adapter.models.TweetEntity;
import es.bit.tweeterApp.driven_adapter.models.UserEntity;
import es.bit.tweeterApp.internal.domain.Tweet;
import es.bit.tweeterApp.internal.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepoTweetsPortAdapterTest {

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