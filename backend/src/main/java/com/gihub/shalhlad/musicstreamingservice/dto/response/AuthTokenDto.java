package com.gihub.shalhlad.musicstreamingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthTokenDto {
    private String accessToken;
    private String refreshToken;
}
