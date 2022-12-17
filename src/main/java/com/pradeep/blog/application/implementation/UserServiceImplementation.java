package com.pradeep.blog.application.implementation;

import com.pradeep.blog.application.constants.common.CommonConstants;
import com.pradeep.blog.application.constants.helper.UserHelper;
import com.pradeep.blog.application.constants.user.UserConstants;
import com.pradeep.blog.application.entities.User;
import com.pradeep.blog.application.exception.GlobalExceptionHandler;
import com.pradeep.blog.application.exception.ResourceNotFoundException;
import com.pradeep.blog.application.payloads.UserDto;
import com.pradeep.blog.application.repositories.UserRepository;
import com.pradeep.blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation extends GlobalExceptionHandler implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    private UserDto getSingleUserDto(Integer userId) {
        return this.userHelper.convertUserToUserDto
                (this.userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)));
    }

    @Override
    public UserDto createUser(UserDto userDto) throws NonUniqueResultException {
        User username = this.userRepository.findByUsername(userDto.getUsername());
        User savedUser;
        if (username == null) {
            User user = this.userHelper.convertUserDtoToUser(userDto);
            savedUser = this.userRepository.save(user);
        } else {
            throw new NonUniqueResultException(UserConstants.USERNAME_ALREADY_EXIST_MESSAGE);
        }
        return this.userHelper.convertUserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) throws NonUniqueResultException {
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setAbout(userDto.getAbout());
        user.setContact(userDto.getContact());
        user.setUsername(userDto.getUsername());
        user.setGender(userDto.getGender());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        User savedUser = this.userRepository.save(user);
        UserDto singleUserDto = userHelper.convertUserToUserDto(savedUser);
        return singleUserDto;
    }

    @Override
    public List<UserDto> searchUserByFirstName(String firstName) {
        List<User> usersByFirstName = this.userRepository.findByFirstName(firstName);
        if (usersByFirstName.size() == 0) {
            throw new NullPointerException(UserConstants.NO_USER_FOUND_BY_FIRST_NAME);
        }
        List<UserDto> userByFirstnameDto = usersByFirstName.stream().map((user) ->
                this.userHelper.convertUserToUserDto(user)).collect(Collectors.toList());
        return userByFirstnameDto;
    }

    @Override
    public List<UserDto> searchUserByLastName(String lastName) {
        List<User> usersByLastName = this.userRepository.findByLastName(lastName);
        if (usersByLastName.size() == 0) {
            throw new NullPointerException(UserConstants.NO_USER_FOUND_BY_LAST_NAME);
        }
        List<UserDto> userByLastnameDto = usersByLastName.stream().map((user) ->
                this.userHelper.convertUserToUserDto(user)).collect(Collectors.toList());

        return userByLastnameDto;
    }

    @Override
    public List<UserDto> searchUserByGender(String gender) {
        List<User> usersByGender = this.userRepository.findByGender(gender);
        if (usersByGender.size() == 0) {

            throw new NullPointerException(UserConstants.NO_USER_FOUND_BY_GENDER);
        }
        List<UserDto> userByGenderDto = usersByGender.stream().map((user) ->
                this.userHelper.convertUserToUserDto(user)).collect(Collectors.toList());
        return userByGenderDto;
    }

    @Override
    public List<UserDto> getAllUsers() throws NullPointerException {
        List<UserDto> userDtos = null;
        List<User> allUsers = this.userRepository.findAll();
        if (allUsers.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_USER_FOUND_MESSAGE);
        } else if (!allUsers.isEmpty()) {
            userDtos = allUsers.stream().map((singleUser) ->
                    userHelper.convertUserToUserDto(singleUser)).collect(Collectors.toList());
            return userDtos;
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        UserDto userDto = this.getSingleUserDto(userId);
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        UserDto userDto = this.getSingleUserDto(userId);
        Integer userDtoId = userDto.getId();
        this.userRepository.deleteById(userDtoId);
    }


}
