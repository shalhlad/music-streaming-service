package com.gihub.shalhlad.musicstreamingservice.service;

import com.gihub.shalhlad.musicstreamingservice.dto.request.RefreshTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserLoginDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserRegistrationDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.AuthTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.UserDto;

public interface AuthService {
    UserDto registerUser(UserRegistrationDto userRegistrationDto);

    AuthTokenDto getAuthToken(UserLoginDto userLoginDto);

    AuthTokenDto refreshAccessToken(RefreshTokenDto refreshTokenDto);
}
