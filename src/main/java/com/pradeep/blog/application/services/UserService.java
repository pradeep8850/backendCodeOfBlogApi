package com.pradeep.blog.application.services;

import com.pradeep.blog.application.entities.User;
import com.pradeep.blog.application.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto) throws Exception;

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer userId);

    void deleteUser(Integer userId);

    UserDto updateUser(UserDto userDto, Integer userId);

    List<UserDto> searchUserByFirstName(String firstName);

    List<UserDto> searchUserByLastName(String lastName);

    List<UserDto> searchUserByGender(String gender);


}
