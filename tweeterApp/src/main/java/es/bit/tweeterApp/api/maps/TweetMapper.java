package es.bit.tweeterApp.api.maps;

import es.bit.tweeterApp.api.models.TweetDto;
import es.bit.tweeterApp.driven_adapter.models.TweetEntity;
import es.bit.tweeterApp.driven_adapter.models.UserEntity;
import es.bit.tweeterApp.internal.domain.Tweet;
import es.bit.tweeterApp.internal.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
}
