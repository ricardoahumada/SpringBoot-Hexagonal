package es.bit;

import es.bit.RepoConfig;
import es.bit.internal.domain.Tweet;
import es.bit.internal.domain.User;
import es.bit.models.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepoConfig.class})
@EnableAutoConfiguration
@Transactional
public class RepoTweetsPortAdapterTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RepoTweetsPortAdapter repoTweetsPortAdapter;

    @PersistenceContext
    EntityManager em;

    @Before
    public void before(){
        UserEntity autor = new UserEntity(null,"ricardo","r@r.com","ppp");
        em.persist(autor);
        System.out.println(autor);
    }

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