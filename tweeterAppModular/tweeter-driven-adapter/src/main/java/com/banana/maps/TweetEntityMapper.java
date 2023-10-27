package com.banana.maps;

import com.banana.internal.domain.Tweet;
import com.banana.internal.domain.User;
import com.banana.models.TweetEntity;
import com.banana.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TweetEntityMapper {
    TweetEntityMapper INSTANCE = Mappers.getMapper( TweetEntityMapper.class );

    public UserEntity userToUserEntity(User user);
    public User userEntityToUser(UserEntity userEntity);

    public List<TweetEntity> tweetsToTweetEntities(List<Tweet> tweets);
    public List<Tweet> tweetEntitiesToTweets(List<TweetEntity> tweetEntities);

    public TweetEntity tweetToTweetEntity(Tweet tweet);
    public Tweet tweetEntityToTweet(TweetEntity tweetEntity);
}
