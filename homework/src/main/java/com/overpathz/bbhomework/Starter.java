package com.overpathz.bbhomework;

import com.overpathz.bbhomework.context.ApplicationContext;
import com.overpathz.bbhomework.context.JavaAnnotationApplicationContext;
import com.overpathz.bbhomework.domain.User;
import com.overpathz.bbhomework.dto.UserDto;
import com.overpathz.bbhomework.mapper.UserMapper;

import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) {
        String packageName = "com.overpathz.bbhomework";
        ApplicationContext context = new JavaAnnotationApplicationContext(packageName);
        UserMapper userMapper = context.getBean("mapper", UserMapper.class);
        UserDto userDto = userMapper.toDto(new User("overpathz", 20, "yu3b%", LocalDate.now()));
        System.out.println(userDto);
    }
}