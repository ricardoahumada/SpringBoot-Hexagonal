package com.banana.tareasproyectoshex.maps;

import com.banana.tareasproyectoshex.models.User;
import com.banana.tareasproyectoshex.models.UserJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface  UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    List<UserJPA> usersToUserJPAs(List<User> users);
    List<User> usersJPAToUsers(List<UserJPA> userJPAs);

    @Mapping(source = "name", target = "name")
    UserJPA userToUserJPA(User user);
    @Mapping(source = "name", target = "name")
    User userJPAToUser(UserJPA userJPA);
}
