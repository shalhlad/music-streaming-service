package com.gihub.shalhlad.musicstreamingservice.service.impl;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gihub.shalhlad.musicstreamingservice.dto.request.RefreshTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserLoginDto;
import com.gihub.shalhlad.musicstreamingservice.dto.request.UserRegistrationDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.AuthTokenDto;
import com.gihub.shalhlad.musicstreamingservice.dto.response.UserDto;
import com.gihub.shalhlad.musicstreamingservice.entity.user.Role;
import com.gihub.shalhlad.musicstreamingservice.entity.user.User;
import com.gihub.shalhlad.musicstreamingservice.exception.IncorrectPasswordException;
import com.gihub.shalhlad.musicstreamingservice.exception.JwtFailedValidationException;
import com.gihub.shalhlad.musicstreamingservice.exception.UserAlreadyExistsException;
import com.gihub.shalhlad.musicstreamingservice.exception.UserNotFoundException;
import com.gihub.shalhlad.musicstreamingservice.mapper.RoleMapper;
import com.gihub.shalhlad.musicstreamingservice.mapper.UserMapper;
import com.gihub.shalhlad.musicstreamingservice.repository.UserRepository;
import com.gihub.shalhlad.musicstreamingservice.security.jwt.JwtProvider;
import com.gihub.shalhlad.musicstreamingservice.service.AuthService;
import com.gihub.shalhlad.musicstreamingservice.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        System.out.println(userRegistrationDto);
        String username = userRegistrationDto.getUsername();
        String email = userRegistrationDto.getEmail();
        String password = userRegistrationDto.getPassword();

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .encryptedPassword(passwordEncoder.encode(password))
                .registrationDate(new Date())
                .roles(List.of(Role.ROLE_USER))
                .build();
        user = userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    @Override
    public AuthTokenDto getAuthToken(UserLoginDto userLoginDto) {
        String usernameOrEmail = userLoginDto.getUsernameOrEmail();
        String password = userLoginDto.getPassword();

        User user;
        if (Utils.isEmail(usernameOrEmail)) {
            user = userRepository.findByEmail(usernameOrEmail)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + usernameOrEmail));
        } else {
            user = userRepository.findByUsername(usernameOrEmail)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + usernameOrEmail));
        }

        if (!passwordEncoder.matches(password, user.getEncryptedPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }

        List<String> stringRoles = roleMapper.toStringList(user.getRoles());

        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), stringRoles);
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail(), stringRoles);

        return new AuthTokenDto(accessToken, refreshToken);
    }

    @Override
    public AuthTokenDto refreshAccessToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        String accessToken;

        try {
            accessToken = jwtProvider.generateAccessTokenFromRefreshToken(refreshToken);
        } catch (JWTVerificationException e) {
            throw new JwtFailedValidationException("Refresh token failed validation");
        }

        return new AuthTokenDto(accessToken, refreshToken);
    }
}
