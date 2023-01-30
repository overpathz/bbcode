package com.overpathz.bbhomework.mapper;

import com.overpathz.bbhomework.context.annotation.Bean;
import com.overpathz.bbhomework.domain.User;
import com.overpathz.bbhomework.dto.UserDto;

@Bean("mapper")
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getUsername(), user.getAge());
    }

    public User toEntity(UserDto userDto) {
        return new User(userDto.username(), userDto.age(), null, null);
    }
}
