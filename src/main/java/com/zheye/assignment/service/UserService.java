package com.zheye.assignment.service;

import com.zheye.assignment.dto.LoginRequest;
import com.zheye.assignment.dto.RegisterRequest;

public interface UserService {
    public void register(RegisterRequest registerRequest);

    public String login(LoginRequest loginRequest);
}
