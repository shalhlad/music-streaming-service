package com.gihub.shalhlad.musicstreamingservice.dto.response;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private Long birthday;
    private Long registrationDate;
}
