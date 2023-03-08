package com.gihub.shalhlad.musicstreamingservice.controller.impl;

import com.gihub.shalhlad.musicstreamingservice.controller.AuthController;
import com.gihub.shalhlad.musicstreamingservice.dto.request.RefreshTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserLoginDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserRegistrationDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.AuthTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.UserDto;
import com.gihub.shalhlad.musicstreamingservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        return authService.registerUser(userRegistrationDto);
    }

    @Override
    public AuthTokenDto login(UserLoginDto userLoginDto) {
        return authService.getAuthToken(userLoginDto);
    }

    @Override
    public AuthTokenDto refreshAccessToken(RefreshTokenDto refreshTokenDto) {
        return authService.refreshAccessToken(refreshTokenDto);
    }
}
