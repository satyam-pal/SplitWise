package com.satyam.splitwise.user;

import com.satyam.splitwise.user.dtos.RegisterUserRequestDto;
import com.satyam.splitwise.user.dtos.UserLoginRequestDto;
import com.satyam.splitwise.user.dtos.UserDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDto> registerUser(@RequestBody RegisterUserRequestDto user){
        var registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailsDto> loginUser(@RequestBody UserLoginRequestDto user){
        var loggedInUser = userService.loginUser(user);
        return ResponseEntity.ok(loggedInUser);
    }



}
