package com.zheye.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;

@Data
public class RegisterRequest {

    private String username;

    private String password;

    @JsonIgnore
    private String salt = UUID.randomUUID().toString().replace("-", "");

}
