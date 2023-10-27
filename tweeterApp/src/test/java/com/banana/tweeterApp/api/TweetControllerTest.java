package com.banana.tweeterApp.api;

import com.banana.tweeterApp.api.models.Message;
import com.banana.tweeterApp.api.models.TweetDto;
import com.banana.tweeterApp.api.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TweetControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private TweetController tweetController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getTweetsValid() {
        ResponseEntity<List<TweetDto>> responseEntity=tweetController.getTweets("hola");
        System.out.println("responseEntity:"+responseEntity);
        assertTrue(responseEntity.getStatusCode()== HttpStatus.OK);
    }

    @Test
    public void getTweetsValidRequest() throws Exception{
        String theme = "hola";
        mockMvc.perform(
                get("/tweets?theme="+theme).accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].autor", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].autor", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void addTweetValid() {
        TweetDto tweetDto=new TweetDto(null,"Nuevo tweet",new Date(),1L);
        ResponseEntity<Message> responseEntity=tweetController.addTweet(tweetDto);
        System.out.println("responseEntity:"+responseEntity);
        assertTrue(responseEntity.getStatusCode()== HttpStatus.CREATED);
    }

    @Test
    public void addTweetValidRequest() throws Exception{
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