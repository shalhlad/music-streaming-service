package com.gihub.shalhlad.musicstreamingservice.controller;

import com.gihub.shalhlad.musicstreamingservice.dto.request.RefreshTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserLoginDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserRegistrationDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.AuthTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/register")
    UserDto registerUser(@RequestBody UserRegistrationDto userRegistrationDto);

    @PostMapping("/login")
    AuthTokenDto login(@RequestBody UserLoginDto userLoginDto);

    @PostMapping("/refresh")
    AuthTokenDto refreshAccessToken(@RequestBody RefreshTokenDto refreshTokenDto);
}
