package es.bit.maps;

import es.bit.internal.domain.Tweet;
import es.bit.models.TweetDto;
import es.bit.model.TweetMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TweetDtoMapper {
    TweetDtoMapper INSTANCE = Mappers.getMapper( TweetDtoMapper.class );

    public List<TweetDto> tweetsToTweetDtos(List<Tweet> tweets);
    public List<Tweet> tweetDtosToTweets(List<TweetDto> tweetDtos);

    @Mapping(source = "autor.id", target = "autor")
    public TweetDto tweetToTweetDto(Tweet tweet);
    @Mapping(source = "autor", target = "autor.id")
    public Tweet tweetDtoToTweet(TweetDto tweetDto);

    @Mapping(target = "type", constant = "TWEET")
    public TweetMessage tweetDtoTotweetMessage(TweetDto tweetDto);
}
