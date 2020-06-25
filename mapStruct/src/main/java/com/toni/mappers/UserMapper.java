package com.toni.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.toni.domain.User;
import com.toni.model.UserCommand;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserCommand userToUserCommand(User user);

    User userCommandToUser(UserCommand userCommand);
}