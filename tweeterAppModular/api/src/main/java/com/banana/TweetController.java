package com.banana;

import com.banana.internal.domain.Tweet;
import com.banana.internal.driver_ports.IServiceTweetsPort;
import com.banana.maps.TweetDtoMapper;
import com.banana.models.Message;
import com.banana.models.TweetDto;
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
        List<TweetDto> tweetDtos= TweetDtoMapper.INSTANCE.tweetsToTweetDtos(tweets);

        return new ResponseEntity(tweetDtos, HttpStatus.OK);
    }

    @RequestMapping(path ="/tweets" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> addTweet(@Valid @RequestBody TweetDto newTweetDto){
        Tweet newTweet= TweetDtoMapper.INSTANCE.tweetDtoToTweet(newTweetDto);
        newTweet= serviceTweetsPort.publishTweet(newTweet);
        TweetDto tweetDto= TweetDtoMapper.INSTANCE.tweetToTweetDto(newTweet);

        if(messagingTemplate!=null) messagingTemplate.convertAndSend("/topic/tweets", TweetDtoMapper.INSTANCE.tweetDtoTotweetMessage(tweetDto));

        if(tweetDto.getId()>0) return new ResponseEntity(new Message("Todo Ook"),HttpStatus.CREATED);
        else return new ResponseEntity(new Message("No se ha creado"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
