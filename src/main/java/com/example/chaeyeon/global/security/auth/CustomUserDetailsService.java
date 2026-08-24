package com.example.chaeyeon.global.security.auth;

import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.User;
import com.example.chaeyeon.domain.student.domain.auth.domain.user.domain.UserRepository;
import com.example.chaeyeon.global.exception.BusinessException;
import com.example.chaeyeon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId){
        Long id = Long.valueOf(userId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}
