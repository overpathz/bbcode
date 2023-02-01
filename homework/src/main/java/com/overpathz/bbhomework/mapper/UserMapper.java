package com.overpathz.bbhomework.mapper;

import com.overpathz.bbhomework.context.annotation.Autowired;
import com.overpathz.bbhomework.context.annotation.Bean;
import com.overpathz.bbhomework.domain.User;
import com.overpathz.bbhomework.dto.UserDto;
import com.overpathz.bbhomework.util.CustomLogger;

@Bean("mapper")
public class UserMapper {

    @Autowired
    private CustomLogger logger;

    public UserDto toDto(User user) {
        logger.error("Converting " + user + " to DTO");
        return new UserDto(user.getUsername(), user.getAge());
    }

    public User toEntity(UserDto userDto) {
        logger.error("Converting " + userDto + " to entity");
        return new User(userDto.username(), userDto.age(), null, null);
    }
}
