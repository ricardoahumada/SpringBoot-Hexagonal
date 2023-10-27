package com.banana.tweeterApp.driven_adapter.maps;

import com.banana.tweeterApp.driven_adapter.models.TweetEntity;
import com.banana.tweeterApp.driven_adapter.models.UserEntity;
import com.banana.tweeterApp.internal.domain.Tweet;
import com.banana.tweeterApp.internal.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TweetMapper {
    TweetMapper INSTANCE = Mappers.getMapper( TweetMapper.class );

    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity userEntity);

    List<TweetEntity> tweetsToTweetEntities(List<Tweet> tweets);
    List<Tweet> tweetEntitiesToTweets(List<TweetEntity> tweetEntities);

    TweetEntity tweetToTweetEntity(Tweet tweet);
    Tweet tweetEntityToTweet(TweetEntity tweetEntity);
}
