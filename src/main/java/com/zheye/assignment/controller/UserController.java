package com.zheye.assignment.controller;

import com.google.common.base.Preconditions;
import com.zheye.assignment.dto.LoginRequest;
import com.zheye.assignment.dto.RegisterRequest;
import com.zheye.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zheye.assignment.constant.RequestConstant.PATH_PREFIX_V1;

@RestController
@ResponseBody
@RequestMapping(PATH_PREFIX_V1)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        Preconditions.checkNotNull(registerRequest.getUsername(), "username cannot be empty");
        Preconditions.checkNotNull(registerRequest.getPassword(), "password cannot be empty");
        userService.register(registerRequest);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Preconditions.checkNotNull(loginRequest.getUsername(), "username cannot be empty");
        Preconditions.checkNotNull(loginRequest.getPassword(), "password cannot be empty");
        return userService.login(loginRequest);
    }

}
