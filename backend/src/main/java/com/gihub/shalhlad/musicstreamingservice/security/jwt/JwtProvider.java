package com.gihub.shalhlad.musicstreamingservice.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gihub.shalhlad.musicstreamingservice.entity.user.Role;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class JwtProvider {
    @Value("${jwt.access-token.secret}")
    private String ACCESS_TOKEN_SECRET;

    @Value("${jwt.access-token.expiration-time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.refresh-token.secret}")
    private String REFRESH_TOKEN_SECRET;

    @Value("${jwt.refresh-token.expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    public void validateAccessToken(String token) throws JWTVerificationException {
        validateToken(token, ACCESS_TOKEN_SECRET);
    }

    public void validateRefreshToken(String token) throws JWTVerificationException {
        validateToken(token, REFRESH_TOKEN_SECRET);
    }

    public String generateAccessToken(String subject, List<String> roles) {
        return generateToken(subject, ACCESS_TOKEN_SECRET, roles, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public String generateAccessTokenFromRefreshToken(String refreshToken) throws JWTVerificationException {
        validateRefreshToken(refreshToken);

        DecodedJWT decodedJWT = JWT.decode(refreshToken);
        String subject = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return generateAccessToken(subject, roles);
    }

    public String generateRefreshToken(String subject, List<String> roles) {
        return generateToken(subject, REFRESH_TOKEN_SECRET, roles, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    public Authentication getAuthenticationFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);

        String subject = decodedJWT.getSubject();
        Collection<? extends GrantedAuthority> roles = decodedJWT.getClaim("roles").asList(Role.class);
        return new UsernamePasswordAuthenticationToken(subject, null, roles);
    }


    private void validateToken(String token, String secret) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWT.require(algorithm).build().verify(token);
    }

    private String generateToken(String subject, String secret, List<String> roles, long expiration) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Date now = new Date();
        Date expires = DateUtils.addMilliseconds(now, (int) expiration);
        return JWT.create()
                .withSubject(subject)
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(now)
                .withExpiresAt(expires)
                .withClaim("roles", roles)
                .sign(algorithm);
    }

}
