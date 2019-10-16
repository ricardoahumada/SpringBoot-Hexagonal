package es.bit.tweeterApp.api;

import es.bit.tweeterApp.api.models.Message;
import es.bit.tweeterApp.internal.domain.Tweet;
import es.bit.tweeterApp.internal.driver_ports.IServiceTweetsPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private IServiceTweetsPort serviceTweetsPort;

    @RequestMapping(path ="/tweets" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tweet> getTweets(@RequestParam String theme){

        return serviceTweetsPort.getTweets(theme);
    }

    @RequestMapping(path ="/tweets" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getTweets(@RequestBody Tweet tweet){
        serviceTweetsPort.publishTweet(tweet);
        return new Message("Todo Ook");
    }

}
