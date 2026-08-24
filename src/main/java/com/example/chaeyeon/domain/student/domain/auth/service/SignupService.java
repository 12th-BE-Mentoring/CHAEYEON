package com.example.chaeyeon.domain.student.domain.auth.service;

import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.User;
import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.UserRepository;
import com.example.chaeyeon.domain.student.domain.auth.dto.SignUpRequest;
import com.example.chaeyeon.global.exception.BusinessException;
import com.example.chaeyeon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.username()))
            throw new BusinessException(ErrorCode.USERNMAE_ALREDY_EXISTS);


        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }
}
