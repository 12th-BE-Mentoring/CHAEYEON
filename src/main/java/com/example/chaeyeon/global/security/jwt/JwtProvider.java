package com.example.chaeyeon.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    public String generateAccessToken(String accountId){
        return generateToken(accountId, ACCESS_TOKEN, jwtProperties.getAccessTokenExpiresIn());
    }

    public String generateRefreshToken(String accountId){
        return generateToken(accountId, REFRESH_TOKEN, jwtProperties.getRefreshTokenExpiresIn());
    }


    public String generateToken(String accountId, String type, Long time){

        Date now = new Date();

        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecretKey()));


        return Jwts.builder()
                .signWith(secretKey)
                .subject(accountId)
                .issuedAt(now)
                .claim("type", type)
                .expiration(new Date(now.getTime() + time))
                .compact();
    }
}
