package com.gihub.shalhlad.musicstreamingservice.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ARTIST,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
