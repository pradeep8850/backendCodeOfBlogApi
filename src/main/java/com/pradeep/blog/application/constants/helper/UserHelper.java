package com.pradeep.blog.application.constants.helper;

import com.pradeep.blog.application.entities.User;
import com.pradeep.blog.application.payloads.UserDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserHelper {

    @Autowired
    private ModelMapper modelMapper;

    public User convertUserDtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
