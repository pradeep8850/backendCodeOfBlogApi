package com.pradeep.blog.application.controllers;

import com.pradeep.blog.application.constants.user.UserConstants;
import com.pradeep.blog.application.exception.ApiResponse;
import com.pradeep.blog.application.payloads.UserDto;
import com.pradeep.blog.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
        UserDto createdUser = this.userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUser = this.userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/id/{userId}")
    private ResponseEntity<UserDto> getSingleUserByUserId(@PathVariable("userId") Integer userId) {
        UserDto singleUser = this.userService.getUserById(userId);
        return ResponseEntity.ok(singleUser);
    }

    @GetMapping("/firstName/{firstName}")
    private ResponseEntity<List<UserDto>> getUsersByFirstName(@PathVariable("firstName") String firstName) {
        List<UserDto> users = this.userService.searchUserByFirstName(firstName);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/lastName/{lastName}")
    private ResponseEntity<List<UserDto>> getUsersByLastName(@PathVariable("lastName") String lastName) {
        List<UserDto> users = this.userService.searchUserByLastName(lastName);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/gender/{gender}")
    private ResponseEntity<List<UserDto>> getUsersByGender(@PathVariable("gender") String gender) {
        List<UserDto> users = this.userService.searchUserByGender(gender);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(UserConstants.USER_DELETED_MESSAGE, true), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUserByUserId(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }
}
