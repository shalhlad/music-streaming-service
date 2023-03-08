package com.gihub.shalhlad.musicstreamingservice.dto.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String usernameOrEmail;
    private String password;
}
