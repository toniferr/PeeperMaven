package com.toni.controllers;

import com.toni.domain.UserCommand;
import com.toni.entities.User;
import com.toni.mappers.UserMapper;

public class UserController {

    User saveUser(UserCommand command) {

        // fake impl
        return UserMapper.INSTANCE.userCommandToUser(command);
    }

}