package com.banana;

import com.banana.config.ApiTestConfig;
import com.banana.internal.domain.Tweet;
import com.banana.internal.domain.User;
import com.banana.internal.driver_ports.IServiceTweetsPort;
import com.banana.models.Message;
import com.banana.models.TweetDto;
import com.banana.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApiTestConfig.class})
@EnableAutoConfiguration
public class TweetControllerTest {

    @Mock
    IServiceTweetsPort iServiceTweetsPort;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Autowired
    @InjectMocks
    private TweetController tweetController;

    private User autor= new User(1L, "ricardo","ric@r.com","");
    private List<Tweet> tweets=new ArrayList(){{
        add(new Tweet(1L, "Hola mundo!",new Date(),autor));
        add(new Tweet(2L,"Qué tal estas?",new Date(),autor));
        add(new Tweet(3L, "Hola, cómo estás",new Date(),autor));
    }};

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
    }

    @Test
    public void getTweetsValid() {
        String tema="hola";
        when(iServiceTweetsPort.getTweets(tema)).thenReturn(tweets.stream().filter(tweet-> tweet.getTexto().toLowerCase().contains(tema)).collect(Collectors.toList()));

        ResponseEntity<List<TweetDto>> responseEntity=tweetController.getTweets("hola");
        System.out.println("responseEntity:"+responseEntity);
        assertTrue(responseEntity.getStatusCode()== HttpStatus.OK);
    }

    @Test
    public void getTweetsValidRequest() throws Exception{
        String theme = "hola";

        when(iServiceTweetsPort.getTweets(theme)).thenReturn(tweets.stream().filter(tweet-> tweet.getTexto().toLowerCase().contains(theme)).collect(Collectors.toList()));

        mockMvc.perform(
                get("/tweets?theme="+theme).accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].autor", is(1)))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].autor", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void addTweetValid() {
        TweetDto tweetDto=new TweetDto(null,"Nuevo tweet",new Date(),1L);
        when(iServiceTweetsPort.publishTweet(any(Tweet.class))).thenReturn(new Tweet(1L,"Hola tweets!",new Date(),autor));

        ResponseEntity<Message> responseEntity=tweetController.addTweet(tweetDto);
        System.out.println("responseEntity:"+responseEntity);
        assertTrue(responseEntity.getStatusCode()== HttpStatus.CREATED);
    }

    @Test
    public void addTweetValidRequest() throws Exception{

        when(iServiceTweetsPort.publishTweet(any(Tweet.class))).thenReturn(new Tweet(1L,"Hola tweets!",new Date(),autor));

        TweetDto tweetDto=new TweetDto(null,"Nuevo tweet request",new Date(),1L);

        mockMvc.perform(
                post("/tweets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.convertObjectToJsonBytes(tweetDto))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje", equalToIgnoringCase("Todo Ook")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}