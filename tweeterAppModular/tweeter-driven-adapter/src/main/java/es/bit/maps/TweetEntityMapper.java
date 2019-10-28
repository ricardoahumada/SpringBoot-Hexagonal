package es.bit.maps;

import es.bit.internal.domain.Tweet;
import es.bit.internal.domain.User;
import es.bit.models.TweetEntity;
import es.bit.models.UserEntity;
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
