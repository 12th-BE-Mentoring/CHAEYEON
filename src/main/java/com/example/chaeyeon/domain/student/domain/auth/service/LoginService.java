package com.example.chaeyeon.domain.student.domain.auth.service;

import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.User;
import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.UserRepository;
import com.example.chaeyeon.domain.student.domain.auth.domain.refreshtoken.domain.RefreshToken;
import com.example.chaeyeon.domain.student.domain.auth.domain.refreshtoken.domain.RefreshTokenRepository;
import com.example.chaeyeon.domain.student.domain.auth.dto.LoginRequest;
import com.example.chaeyeon.domain.student.domain.auth.dto.TokenResponse;
import com.example.chaeyeon.global.exception.BusinessException;
import com.example.chaeyeon.global.exception.ErrorCode;
import com.example.chaeyeon.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.usernmae())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!user.getUsername().equals(request.usernmae()))
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);

        String accessToken = jwtProvider.generateAccessToken(user.getId().toString());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId().toString());

        RefreshToken token = RefreshToken.builder()
                .userId(user.getId())
                .token(refreshToken)
                .expiresAt(jwtProvider.getExpiration(refreshToken))
                .build();

        refreshTokenRepository.save(token);

        return TokenResponse.of(accessToken, refreshToken);

    }
}
