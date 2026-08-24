package com.example.chaeyeon.domain.auth.service;

import com.example.chaeyeon.domain.auth.domain.refreshtoken.RefreshToken;
import com.example.chaeyeon.domain.auth.domain.refreshtoken.RefreshTokenRepository;
import com.example.chaeyeon.domain.auth.dto.ReissueRequest;
import com.example.chaeyeon.domain.auth.dto.TokenResponse;
import com.example.chaeyeon.global.exception.BusinessException;
import com.example.chaeyeon.global.exception.ErrorCode;
import com.example.chaeyeon.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReissueService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse reissue(ReissueRequest request){
        jwtProvider.validateRefreshToken(request.refreshToken());

        RefreshToken savedRefreshToken
                = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_INVALID));

        Long userId = savedRefreshToken.getUserId();
        refreshTokenRepository.delete(savedRefreshToken);

        String accessToken = jwtProvider.generateAccessToken(userId.toString());
        String refreshToken = jwtProvider.generateRefreshToken(userId.toString());

        RefreshToken newRefreshToken = RefreshToken.builder()
                .token(refreshToken)
                .userId(userId)
                .expiresAt(jwtProvider.getExpiration(refreshToken))
                .build();

        refreshTokenRepository.save(newRefreshToken);
        return TokenResponse.of(accessToken, refreshToken);
    }
}
