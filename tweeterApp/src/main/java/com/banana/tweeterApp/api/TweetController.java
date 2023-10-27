package com.banana.tweeterApp.api;

import com.banana.tweeterApp.api.maps.TweetMapper;
import com.banana.tweeterApp.api.models.Message;
import com.banana.tweeterApp.api.models.TweetDto;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.driver_ports.IServiceTweetsPort;
import com.banana.tweeterApp.websocket.model.TweetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private IServiceTweetsPort serviceTweetsPort;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @RequestMapping(path ="/tweets" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TweetDto>> getTweets(@RequestParam String theme){

        List<Tweet> tweets= serviceTweetsPort.getTweets(theme);
        List<TweetDto> tweetDtos= TweetMapper.INSTANCE.tweetsToTweetDtos(tweets);

        return new ResponseEntity(tweetDtos, HttpStatus.OK);
    }

    @RequestMapping(path ="/tweets" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> addTweet(@Valid @RequestBody TweetDto newTweetDto){
        Tweet newTweet= TweetMapper.INSTANCE.tweetDtoToTweet(newTweetDto);
        newTweet= serviceTweetsPort.publishTweet(newTweet);
        TweetDto tweetDto=TweetMapper.INSTANCE.tweetToTweetDto(newTweet);

        messagingTemplate.convertAndSend("/topic/tweets", TweetMapper.INSTANCE.tweetDtoTotweetMessage(tweetDto));

        if(tweetDto.getId()>0) return new ResponseEntity(new Message("Todo Ook"),HttpStatus.CREATED);
        else return new ResponseEntity(new Message("No se ha creado"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
