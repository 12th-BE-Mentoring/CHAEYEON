package com.example.chaeyeon.global.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpiresIn;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpiresIn;

    @Value("${jwt.secret}")
    private String secretKey;
}
