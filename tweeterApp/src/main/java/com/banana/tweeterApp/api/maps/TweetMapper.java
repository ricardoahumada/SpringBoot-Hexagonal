package com.banana.tweeterApp.api.maps;

import com.banana.tweeterApp.api.models.TweetDto;
import com.banana.tweeterApp.driven_adapter.models.TweetEntity;
import com.banana.tweeterApp.driven_adapter.models.UserEntity;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.domain.User;
import com.banana.tweeterApp.websocket.model.TweetMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TweetMapper {
    TweetMapper INSTANCE = Mappers.getMapper( TweetMapper.class );

    List<TweetDto> tweetsToTweetDtos(List<Tweet> tweets);
    List<Tweet> tweetDtosToTweets(List<TweetDto> tweetDtos);

    @Mapping(source = "autor.id", target = "autor")
    TweetDto tweetToTweetDto(Tweet tweet);
    @Mapping(source = "autor", target = "autor.id")
    Tweet tweetDtoToTweet(TweetDto tweetDto);

    @Mapping(target = "type", constant = "TWEET")
    TweetMessage tweetDtoTotweetMessage(TweetDto tweetDto);
}
