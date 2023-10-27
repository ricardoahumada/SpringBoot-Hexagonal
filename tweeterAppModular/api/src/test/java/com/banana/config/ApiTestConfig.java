package com.banana.config;

import com.banana.TweetController;
import com.banana.internal.domain.Tweet;
import com.banana.internal.driver_ports.IServiceTweetsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.banana"})
public class ApiTestConfig {

    @Bean
    public IServiceTweetsPort getService(){

        class ServicePort implements IServiceTweetsPort{

            @Override
            public List<Tweet> getTweets(String theme) {
                return null;
            }

            @Override
            public Tweet publishTweet(Tweet tweet) {
                return null;
            }
        }

        return new ServicePort();
    }


}
