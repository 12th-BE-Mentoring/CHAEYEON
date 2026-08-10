package com.example.chaeyeon.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;
    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    public JwtProvider(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String accountId){
        return generateToken(accountId, ACCESS_TOKEN, jwtProperties.getAccessTokenExpiresIn());
    }

    public String generateRefreshToken(String accountId){
        return generateToken(accountId, REFRESH_TOKEN, jwtProperties.getRefreshTokenExpiresIn());
    }


    public String generateToken(String accountId, String type, Long time){

        Date now = new Date();

        return Jwts.builder()
                .signWith(secretKey)
                .subject(accountId)
                .issuedAt(now)
                .claim("type", type)
                .expiration(new Date(now.getTime() + time))
                .compact();
    }
}
