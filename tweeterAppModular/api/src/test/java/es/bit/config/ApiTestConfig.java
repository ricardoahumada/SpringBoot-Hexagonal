package es.bit.config;

import es.bit.TweetController;
import es.bit.internal.domain.Tweet;
import es.bit.internal.driver_ports.IServiceTweetsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"es.bit"})
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
